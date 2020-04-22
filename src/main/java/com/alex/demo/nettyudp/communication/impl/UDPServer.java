/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.communication.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.SmartLifecycle;

import com.alex.demo.nettyudp.codec.MessageCodec;
import com.alex.demo.nettyudp.codec.UDPMessageCodec;
import com.alex.demo.nettyudp.communication.Message;
import com.alex.demo.nettyudp.communication.UdpCallback;
import com.alex.demo.nettyudp.connection.Connection;
import com.alex.demo.nettyudp.handler.ServerHandler;
import com.alex.demo.nettyudp.packet.UDPPacket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 */
@Slf4j
public class UDPServer implements Connection, SmartLifecycle, UdpCallback {

	@Autowired
	private UdpProperties properties;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageCodec codec;

	@Value("${nettyudp.local.ip}")
	private String localIP;

	private Channel channel;

	private int port;

	private volatile boolean running;

	protected final ReentrantLock lifecycleLock = new ReentrantLock();

	private UdpCallback callback = this;

	private ExecutorService pool = Executors.newFixedThreadPool(1);

	private Map<Integer, SyncSubscriber<Message<?>>> subscribers = new ConcurrentHashMap<>();

	public UDPServer(int port) {
		this.port = port;
	}

	public UDPServer(int port, UdpCallback callback) {
		this.port = port;
		this.callback = callback;
	}

	public UDPServer(int port, MessageCodec codec) {
		this.port = port;
		this.codec = codec;
	}

	/**
	 * 自启动
	 *
	 * @see org.springframework.context.Lifecycle#start()
	 */
	@Override
	public void start() {
		lifecycleLock.lock();
		try {
			if (!running) {
				doStart();
				running = true;
				log.info("started " + this);
			}
		} finally {
			lifecycleLock.unlock();
		}
	}

	/**
	 * @see org.springframework.context.Lifecycle#stop()
	 */
	@Override
	public void stop() {
		lifecycleLock.lock();
		try {
			if (running) {
				doStop();
				running = false;
				log.info("stopped " + this);
			}
		} finally {
			lifecycleLock.unlock();
		}
	}

	private void doStart() {
		Bootstrap bootstrap = new Bootstrap();
		NioEventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group);

		bootstrap.channel(NioDatagramChannel.class);
		bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(properties.getRecvByteAllocate()));
		bootstrap.option(ChannelOption.SO_RCVBUF, properties.getRcvBuf());// 务必设置这个参数，否则可能导致收到的报文不全，因为接收缓存区满了
		bootstrap.option(ChannelOption.SO_REUSEADDR, properties.isReuseAddr());
		bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {

			@Override
			protected void initChannel(NioDatagramChannel ch) throws Exception {
				ch.pipeline().addLast("MessageCodec", new UDPMessageCodec());
				ch.pipeline().addLast("ServerHandler", new ServerHandler(UDPServer.this));
			}
		});
		try {
			ChannelFuture future = bootstrap.bind(localIP, port).sync().await();
			channel = future.channel();
			log.info("bind:" + channel.localAddress());
		} catch (Exception e) {
			log.error("监听端口：" + port + "失败", e);
		}
	}

	/**
	 *
	 */
	private void doStop() {
		// do nothing
	}

	/**
	 */
	@Override
	public Message<?> send(Message<?> packet, int timeout) throws Exception {
		SyncSubscriber<Message<?>> subscriber = new SyncSubscriber<>(packet.getSid());
		subscribers.put(packet.getSid(), subscriber);
		send(packet);
		Message<?> result = subscriber.get(timeout, TimeUnit.SECONDS);
		subscriber = null;
		subscribers.remove(packet.getSid());
		return result;
	}

	/**
	 */
	@Override
	public void sendAsync(Message<?> packet) {
		send(packet);
	}

	/**
	 * 编码后发送
	 *
	 * @param packet
	 */
	private void send(Message<?> packet) {
		if (codec == null) {
			throw new NullPointerException("MessageCodec is NULL");
		}
		UDPPacket msg = codec.encode(packet);
		System.err.println(System.currentTimeMillis() + " " + port + ": send " + msg);
		channel.writeAndFlush(msg);
	}

	/**
	 * handler收到的数据会到达这里
	 *
	 * @param msg
	 */
	public void messageArrived(UDPPacket msg) {
		System.err.println(System.currentTimeMillis() + " " + port + ": receive " + msg);
		Message<?> packet = codec.decode(msg);
		callback.messageArrived(packet);
	}

	/**
	 * @see org.springframework.context.Lifecycle#isRunning()
	 */
	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public void messageArrived(Message<?> packet) {
		pool.submit(() -> {
			SyncSubscriber<Message<?>> subscriber = subscribers.get(packet.getSid());
			if (subscriber != null && subscriber.getSid() == packet.getSid()) {
				// 转同步
				subscriber.setResponse(packet);
			} else {
				System.err.println(port + ":" + "publish");
				// 推给观察者
				publisher.publishEvent(packet.getData());
			}
		});
	}

}

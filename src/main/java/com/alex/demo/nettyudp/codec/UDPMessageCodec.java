/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.codec;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.alex.demo.nettyudp.packet.UDPPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageCodec;

/**
 * @Author Alex
 * @Created Dec 2, 2019 3:25:15 PM
 * @Description
 *              <p>
 */
public class UDPMessageCodec extends MessageToMessageCodec<DatagramPacket, UDPPacket> {

	/**
	 * 这个方法是接收到报文的首个入口
	 * 
	 * @see io.netty.handler.codec.MessageToMessageCodec#decode(io.netty.channel.ChannelHandlerContext, Object, List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) throws Exception {
		if (packet.content().readableBytes() < 12) { // 根据通信协议 12=1+1+2+4+4
			return;
		}
		ByteBuf buf = packet.content();
		int pos = buf.readerIndex();
		int length = buf.getIntLE(pos + 8);
		if (packet.content().readableBytes() >= length + 12) {
			byte[] bs = new byte[length + 12];
			buf.readBytes(bs);
			UDPPacket msg = decode(bs);
			out.add(msg);
		}
	}

	/**
	 * @param bs
	 * @return
	 */
	private UDPPacket decode(byte[] bs) {
		ByteBuffer buffer = ByteBuffer.wrap(bs).order(ByteOrder.LITTLE_ENDIAN);
		UDPPacket msg = new UDPPacket();
		buffer.get();
		buffer.get();
		msg.setSid(Short.toUnsignedInt(buffer.getShort()));
		msg.setData(bs);
		return msg;
	}

	/**
	 * @see io.netty.handler.codec.MessageToMessageCodec#encode(io.netty.channel.ChannelHandlerContext, Object, List)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, UDPPacket msg, List<Object> out) throws Exception {
		InetSocketAddress address = new InetSocketAddress(msg.getIp(), msg.getPort());
		DatagramPacket packet = new DatagramPacket(Unpooled.copiedBuffer(msg.getData()), address);
		ctx.writeAndFlush(packet);
	}

}

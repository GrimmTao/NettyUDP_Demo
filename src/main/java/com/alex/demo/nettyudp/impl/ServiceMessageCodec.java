/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alex.demo.nettyudp.codec.MessageCodec;
import com.alex.demo.nettyudp.communication.Message;
import com.alex.demo.nettyudp.communication.impl.TransportMessage;
import com.alex.demo.nettyudp.message.ReceiveFactory;
import com.alex.demo.nettyudp.message.ReceiveMessage;
import com.alex.demo.nettyudp.message.SendMessage;
import com.alex.demo.nettyudp.packet.UDPPacket;
import com.alex.demo.nettyudp.util.ByteUtil;

/**
 * @Author Alex
 * @Created Dec 2, 2019 6:57:50 PM
 * @Description
 *              <p>
 */
@Component
public class ServiceMessageCodec implements MessageCodec {

	@Autowired
	private Map<String, ReceiveFactory> factories;

	/**
	 * 和本例程通信的对方IP
	 */
	@Value("${nettyudp.other.ip}")
	private String otherIP;

	/**
	 * 和本例程通信的对方端口
	 */
	@Value("${nettyudp.other.port}")
	private int otherPort;

	/**
	 * @see com.alex.demo.nettyudp.codec.MessageCodec#encode(com.alex.demo.nettyudp.communication.Message)
	 */
	@Override
	public UDPPacket encode(Message<?> message) {
		Object data = message.getData();
		UDPPacket packet = new UDPPacket();
		packet.setIp(otherIP);
		packet.setPort(otherPort);
		if (data instanceof SendMessage) {
			SendMessage request = (SendMessage) data;
			packet.setSid(message.getSid());
			ByteBuffer buffer = ByteBuffer.allocate(5 + request.toBytes().length).order(ByteOrder.LITTLE_ENDIAN);
			buffer.putInt(message.getSid());
			buffer.putInt(request.toBytes().length);
			buffer.put(request.toBytes());
			packet.setData(buffer.array());
		}
		System.out.println("send-- " + ByteUtil.bytes2HexString(packet.getData()));
		return packet;
	}

	/**
	 * @see com.alex.demo.nettyudp.codec.MessageCodec#decode(com.alex.demo.nettyudp.packet.UDPPacket)
	 */
	@Override
	public Message<?> decode(UDPPacket packet) {
		TransportMessage<ReceiveMessage> message = new TransportMessage<>();
		ByteBuffer buffer = ByteBuffer.wrap(packet.getData()).order(ByteOrder.LITTLE_ENDIAN);
		System.out.println("receive-- " + ByteUtil.bytes2HexString(buffer.array()));

		message.setTarget(Byte.toUnsignedInt(buffer.get()));
		message.setSource(Byte.toUnsignedInt(buffer.get()));
		message.setSid(Short.toUnsignedInt(buffer.getShort()));
		message.setCounter(buffer.getInt());

		int length = buffer.getInt();
		byte[] datas = new byte[length];
		buffer.get(datas);

		ReceiveFactory factory = factories.get("CUSTOMIZEFACTORY");// 实际使用建议通过factories.get(String.valueOf(packet.getSid()));
		ReceiveMessage receive = factory.create(datas);
		message.setData(receive);
		return message;
	}

}

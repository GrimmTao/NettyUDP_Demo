/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.demo.nettyudp.communication.impl.CommunicationImpl;
import com.alex.demo.nettyudp.communication.impl.TransportMessage;

/**
 * @Author Alex
 * @Created Dec 2020/4/22 15:02
 * @Description
 *              <p>
 */
@Service
public class CustomizeMessageServiceImpl implements CustomizeMessageService {

	@Autowired
	public CommunicationImpl communicationImpl;

	@Override
	public void asyncSend() {
		CustomizeSendMessage message = new CustomizeSendMessage();
		message.setField_1((byte) 0x01);
		message.setField_2(1);
		TransportMessage<CustomizeSendMessage> transportMessage = new TransportMessage<>();
		// transportMessage.setSid();//TODO 具体sid是多少，视实际情况而定
		transportMessage.setSource(DemoProgram.DEMO_1.getCode());
		transportMessage.setTarget(DemoProgram.DEMO_2.getCode());
		transportMessage.setData(message);
		communicationImpl.sendAsync(transportMessage);
	}

	@Override
	public void send() {
		CustomizeSendMessage message = new CustomizeSendMessage();
		message.setField_1((byte) 0x01);
		message.setField_2(1);
		TransportMessage<CustomizeSendMessage> transportMessage = new TransportMessage<>();
		// transportMessage.setSid();//TODO 具体sid是多少，视实际情况而定
		transportMessage.setSource(DemoProgram.DEMO_1.getCode());
		transportMessage.setTarget(DemoProgram.DEMO_2.getCode());
		transportMessage.setData(message);
		try {
			communicationImpl.send(transportMessage, 5);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------超时未收到报文反馈---------");
		}
	}
}

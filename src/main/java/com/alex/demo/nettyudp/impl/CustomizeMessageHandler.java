/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:04
 * @Description
 *              <p>
 *              处理接收到自定义报文的Handler
 */
@Configuration
public class CustomizeMessageHandler {

	@Autowired
	private CustomizeMessageService service;

	@EventListener
	@Async
	public void receiveCustomizeMessage(CustomizeReceiveMessage message) {
		System.out.println("-----收到指定报文-----");
		// service.asyncSend();
		// service.send();
	}
}

/*******************************************************************************
 * Copyright (c) 2019, 2019 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.communication.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alex.demo.nettyudp.communication.Communication;
import com.alex.demo.nettyudp.communication.Message;
import com.alex.demo.nettyudp.connection.Connection;
import com.alex.demo.nettyudp.connection.TargetConnSelector;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 */
@Component
public class CommunicationImpl implements Communication {

	@Autowired(required = false)
	private Map<String, Connection> connectionMap;

	@Autowired(required = false)
	private TargetConnSelector selector;

	/**
	 * @see com.alex.demo.nettyudp.communication.Communication#send(com.alex.demo.nettyudp.communication.Message, int)
	 */
	@Override
	public Message<?> send(Message<?> message, int timeout) throws Exception {
		Connection connection = selector.select(message.getTarget(), connectionMap);
		if (connection != null) {
			return connection.send(message, timeout);
		}
		throw new Exception("target connection does not exist");
	}

	/**
	 * @see com.alex.demo.nettyudp.communication.Communication#sendAsync(com.alex.demo.nettyudp.communication.Message)
	 */
	@Override
	public void sendAsync(Message<?> message) {
		Connection connection = selector.select(message.getTarget(), connectionMap);
		if (connection != null) {
			connection.sendAsync(message);
		}
	}

}

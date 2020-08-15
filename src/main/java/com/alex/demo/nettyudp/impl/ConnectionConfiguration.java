/*******************************************************************************
 * Copyright (c) 2019, 2019 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alex.demo.nettyudp.communication.impl.UDPServer;
import com.alex.demo.nettyudp.connection.Connection;

/**
 * @Author Alex
 * @Created Dec 2, 2019 5:29:08 PM
 * @Description
 *              <p>
 *              初始化本例程的通信连接
 */
@Configuration
public class ConnectionConfiguration {

	/**
	 * 壳子和控制管理程序的通信端口
	 */
	@Value("${nettyudp.local.port}")
	private int local_port;

	@Bean("DEMO_1")
	public Connection initConnection() {
		return new UDPServer(local_port);
	}

}

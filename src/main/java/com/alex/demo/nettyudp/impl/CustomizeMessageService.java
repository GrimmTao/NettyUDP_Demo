/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

/**
 * @Author Alex
 * @Created Dec 2020/4/22 15:01
 * @Description
 *              <p>
 */
public interface CustomizeMessageService {

	/**
	 * 异步发送，不需要等待返回结果
	 */
	void asyncSend();

	/**
	 * 同步发送，需要等待返回结果
	 */
	void send();
}

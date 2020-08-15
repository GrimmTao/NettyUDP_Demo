/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.message;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:14
 * @Description
 *              <p>
 *              接收的报文
 */
public interface ReceiveMessage {

	/**
	 * 生成报文对象
	 *
	 * @param datas
	 */
	void parse(byte[] datas);
}

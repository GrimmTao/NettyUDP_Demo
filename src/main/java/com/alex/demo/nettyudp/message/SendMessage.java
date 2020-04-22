/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.message;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:14
 * @Description
 *              <p>
 *              发送的报文
 */
public interface SendMessage {

	/**
	 * 生成二进制报文数据
	 */
	byte[] toBytes();
}

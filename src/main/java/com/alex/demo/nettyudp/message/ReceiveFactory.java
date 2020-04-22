/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.message;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:18
 * @Description
 *              <p>
 *              接收报文的工厂类
 */
public interface ReceiveFactory {

	ReceiveMessage create(byte[] datas);
}

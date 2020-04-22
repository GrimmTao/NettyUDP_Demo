/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import org.springframework.stereotype.Component;

import com.alex.demo.nettyudp.message.ReceiveFactory;
import com.alex.demo.nettyudp.message.ReceiveMessage;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:55
 * @Description
 *              <p>
 *              生成CustomizeReceiveMessage的工厂
 */
@Component("CUSTOMIZEFACTORY") // 实际使用会有多个Factory实现类，可以用message 的source 和 packet 的sid 组合来区别
public class CustomizeFactory implements ReceiveFactory {

	@Override
	public ReceiveMessage create(byte[] datas) {
		CustomizeReceiveMessage message = new CustomizeReceiveMessage();
		message.parse(datas);
		return message;
	}
}

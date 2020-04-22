/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.alex.demo.nettyudp.message.ReceiveMessage;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:05
 * @Description
 *              <p>
 *              自定义接收报文
 */
@Data
public class CustomizeReceiveMessage implements ReceiveMessage {

	// TODO 具体有哪些属性，视实际情况而定

	private byte field_1;

	private int field_2;

	@Override
	public void parse(byte[] datas) {
		final ByteBuffer buffer = ByteBuffer.wrap(datas).order(ByteOrder.LITTLE_ENDIAN);
		// TODO 将datas解析成 field_1、field_2
		field_1 = buffer.get();
		field_2 = buffer.getInt();
	}
}

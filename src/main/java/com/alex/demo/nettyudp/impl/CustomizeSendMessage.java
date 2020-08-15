/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.alex.demo.nettyudp.message.SendMessage;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/4/21 18:06
 * @Description
 *              <p>
 *              自定义发送报文
 */
@Data
public class CustomizeSendMessage implements SendMessage {

	// TODO 具体有哪些属性，试实际情况而定

	private byte field_1;

	private int field_2;

	@Override
	public byte[] toBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(1 + 4).order(ByteOrder.LITTLE_ENDIAN);
		buffer.put(field_1);
		buffer.putInt(field_2);
		return buffer.array();
	}
}

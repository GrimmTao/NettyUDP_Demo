/*******************************************************************************
 * Copyright (c) 2019, 2019 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.communication.impl;

import com.alex.demo.nettyudp.communication.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 */
@Data
@NoArgsConstructor
public class TransportMessage<T> implements Message<T> {

	private int sid;

	private int source;

	private int target;

	private int counter;

	private T data;

	/**
	 * @param sid
	 * @param source
	 * @param target
	 * @param data
	 */
	public TransportMessage(int sid, int source, int target, T data) {
		this.sid = sid;
		this.source = source;
		this.target = target;
		this.data = data;
	}

}

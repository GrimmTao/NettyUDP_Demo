/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.communication;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 */
public interface Message<T> {

	int getSid();

	int getSource();

	int getTarget();

	int getCounter();

	T getData();
}

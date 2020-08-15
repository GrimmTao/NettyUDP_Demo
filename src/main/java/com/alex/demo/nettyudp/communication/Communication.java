/*******************************************************************************
 * Copyright (c) 2019, 2019 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.communication;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 *              通信模块接口
 */
public interface Communication {

	/**
	 * 同步发送
	 * 
	 * @param packet
	 * @param timeout
	 *            等待时间限制，单位秒
	 * @return
	 * @throws Exception
	 */
	Message<?> send(Message<?> packet, int timeout) throws Exception;

	/**
	 * 异步发送
	 * 
	 * @param packet
	 */
	void sendAsync(Message<?> packet);
}

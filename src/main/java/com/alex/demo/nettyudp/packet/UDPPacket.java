/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.packet;

import lombok.Data;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 */
@Data
public class UDPPacket {

	private int sid;

	private String ip;

	private int port;

	private byte[] data;

	// 这个port是用来处理和Python算法通信时，算法每次启动后绑定的端口随机这一情况的
	// private int port;

}

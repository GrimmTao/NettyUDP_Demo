/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.communication.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @Author Alex
 * @Created Jan 10, 2020 9:22:10 AM
 * @Description
 *              <p>
 */
@Component
public class UdpProperties {

	/**
	 * 最大可接收字段长度
	 */
	@Value("${connection.udp.recvbyte}")
	@Getter
	private int recvByteAllocate;

	@Value("${connection.udp.rvcbuf}")
	@Getter
	private int rcvBuf;

	@Value("${connection.udp.reuseaddr}")
	@Getter
	private boolean reuseAddr;
}

/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.codec;

import com.alex.demo.nettyudp.communication.Message;
import com.alex.demo.nettyudp.packet.UDPPacket;

/**
 * @Author Alex
 * @Created Dec 2, 2019 3:46:37 PM
 * @Description
 *              <p>
 *              编解码，Message对象和UDPPacket互转
 */
public interface MessageCodec {

	UDPPacket encode(Message<?> message);

	Message<?> decode(UDPPacket packet);
}

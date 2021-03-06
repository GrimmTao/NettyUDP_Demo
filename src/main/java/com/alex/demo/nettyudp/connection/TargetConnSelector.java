/*******************************************************************************
 * Copyright (c) 2019, 2019 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.connection;

import java.util.Map;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 *              连接实例选择器
 */
public interface TargetConnSelector {

	/**
	 * 根据target返回发送的连接实例
	 *
	 * @param target
	 * @param connectionMap
	 * @return
	 */
	Connection select(int target, Map<String, Connection> connectionMap);
}

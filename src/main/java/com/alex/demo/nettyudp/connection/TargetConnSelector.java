/*******************************************************************************
 * Copyright (c) 2019, 2019 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.nettyudp.connection;

import java.util.Map;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 *              连接实例挑选规则
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

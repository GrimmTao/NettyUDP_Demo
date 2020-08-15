/*******************************************************************************
 * Copyright (c) 2019, 2019 Alex.
 ******************************************************************************/
package com.alex.demo.nettyudp.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alex.demo.nettyudp.connection.Connection;
import com.alex.demo.nettyudp.connection.TargetConnSelector;

import lombok.Getter;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 */
@Component
public class TargetConnSelectorImpl implements TargetConnSelector {

	/**
	 * @see com.alex.demo.nettyudp.connection.TargetConnSelector#select(int, java.util.Map)
	 */
	@Override
	public Connection select(int target, Map<String, Connection> connectionMap) {
		// 此处应该有一个target和programID 的映射关系，可以是一个枚举类 ，如下方的DemoProgram
		String programId = DemoProgram.getDemoProgram(target).name();
		return connectionMap.get(programId);
	}
}

enum DemoProgram {

	DEMO_1(1),

	DEMO_2(2),

	DEMO_3(3);

	@Getter
	private int code;

	/**
	 * @param code
	 */
	private DemoProgram(int code) {
		this.code = code;
	}

	public static DemoProgram getDemoProgram(int code) {
		for (DemoProgram demoProgram : DemoProgram.values()) {
			if (code == demoProgram.getCode()) {
				return demoProgram;
			}
		}
		return null;
	}

}

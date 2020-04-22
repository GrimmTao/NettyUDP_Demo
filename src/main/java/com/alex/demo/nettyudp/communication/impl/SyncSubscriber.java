package com.alex.demo.nettyudp.communication.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import lombok.Getter;

/**
 * @Author Alex
 * @Created 2018年11月9日 下午1:47:33
 * @Description
 *              <p>
 *              异步转同步辅助类
 */
class SyncSubscriber<T> {

	@Getter
	private int sid;

	private T response;

	private final CountDownLatch latch = new CountDownLatch(1);

	/**
	 * @param sid
	 */
	public SyncSubscriber(int sid) {
		this.sid = sid;
	}

	public boolean isDone() {
		return response != null;
	}

	public T get() throws InterruptedException {
		latch.await();
		return response;
	}

	public T get(final long timeout, final TimeUnit unit) throws InterruptedException {
		if (latch.await(timeout, unit)) {
			return response;
		}
		return null;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	protected void setResponse(final T response) {
		this.response = response;
		latch.countDown();
	}
}

package com.alex.demo.nettyudp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 注意若不加此注解，无法实现异步接收报文 （此注解配合 CustomizeMessageHandler类 方法上的@Async注解一起使用）
public class NettyudpApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyudpApplication.class, args);
	}

}

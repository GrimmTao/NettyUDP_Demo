# 说明
基于Netty实现的UDP通信

利用SpringBoot实现

com.alex.demo.nettyudp.impl包下的内容为具体的实现:

ConnectionConfiguration.java: 初始化一个本地连接;

CustomizeMessageHandler.java: 处理接收到自定义报文类型的Handler;

CustomizeReceiveMessage.java: 自定义的接收报文;

CustomizeSendMessage.java: 自定义的发送报文;

ServiceMessageCodec.java: 编解码处理

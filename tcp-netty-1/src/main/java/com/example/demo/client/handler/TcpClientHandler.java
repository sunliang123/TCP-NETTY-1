package com.example.demo.client.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.rabbitmq.RabbitmqConfiguration;
import com.example.demo.rabbitmq.RabbitmqProducer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {

	@Autowired
	private RabbitmqProducer rabbitmqProducer;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("client接收到服务器返回的消息:" + msg);

		rabbitmqProducer.sendMessage(RabbitmqConfiguration.tcpNetty, msg);
	}

}

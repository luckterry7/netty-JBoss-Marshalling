package cn.terry.netty.JBoss_Marshalling.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import cn.terry.netty.JBoss_Marshalling.factory.MarshallingCodeFactory;
import cn.terry.netty.JBoss_Marshalling.server.SubReqServerHandler;


public class SubReqClient {
	public void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>(){
				
				@Override
				protected void initChannel(SocketChannel ch)
						throws Exception {
					ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder())
					.addLast(MarshallingCodeFactory.buildMarshallingEncoder())
					.addLast(new SubReqClientHandler());	
				}
			});
			//绑定端口，调用同步阻塞方法sysn，等待绑定操作完成
			ChannelFuture f = b.connect(host,port).sync();
			
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	
	}
	
	public static void main(String[] args) {
		new SubReqClient().connect("127.0.0.1", 8081);
	}
}

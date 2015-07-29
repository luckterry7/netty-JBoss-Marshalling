package cn.terry.netty.JBoss_Marshalling.server;

import cn.terry.netty.JBoss_Marshalling.factory.MarshallingCodeFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SubReqServer {
	public void bind(int port){
		//建立线程组，用于接受客户端的连接
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		
		//建立线程组，用于进行SocketChannel的网络读写
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 100)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder())
								.addLast(MarshallingCodeFactory.buildMarshallingEncoder())
								.addLast(new SubReqServerHandler());	
				}
					
			});

			//绑定端口，调用同步阻塞方法sysn，等待绑定操作完成
			ChannelFuture f = b.bind(port).sync();
			System.out.println("server is started");
			//调用sync阻塞方法，等待服务端链路关闭之后main函数才退出
			f.channel().closeFuture().sync();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args) {
		new SubReqServer().bind(8081);
	}
	
}

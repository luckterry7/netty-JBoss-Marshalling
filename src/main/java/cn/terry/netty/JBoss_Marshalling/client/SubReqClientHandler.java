package cn.terry.netty.JBoss_Marshalling.client;

import cn.terry.netty.JBoss_Marshalling.model.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter{

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i= 0;i<10;i++){
			ctx.write(getSubReq(i));
		}
		ctx.flush();
	}
	
	private SubscribeReq getSubReq(int i){
		SubscribeReq req = new SubscribeReq();
		req.setAddress("深圳福田区xxx" + i);
		req.setProductNumber("12132");
		req.setProductName("nerry for Marshalling");
		req.setUserName("terry");
		req.setSubReqID(i);
		return req;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("receive server response : {" + msg + "}");
	}
	
}

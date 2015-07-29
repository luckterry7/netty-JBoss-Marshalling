package cn.terry.netty.JBoss_Marshalling.server;

import cn.terry.netty.JBoss_Marshalling.model.SubscribeReq;
import cn.terry.netty.JBoss_Marshalling.model.SubscribeResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter{

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		SubscribeReq req = (SubscribeReq) msg;
		if("terry".equalsIgnoreCase(req.getUserName())){
			System.out.println("server accept client subscribe req : {" + req.toString() + "}");
			SubscribeResp resp = getResp(req.getSubReqID());
			ctx.writeAndFlush(resp);
		}
		
		
	}

	private SubscribeResp getResp(int subReqID) {
		SubscribeResp resp = new SubscribeResp();
		resp.setSubRespID(subReqID);
		resp.setRespCode(0);
		resp.setDesc("netty book order succeed!");
		return resp;
	}
	
}

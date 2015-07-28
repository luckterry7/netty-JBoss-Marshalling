package cn.terry.netty.JBoss_Marshalling.model;

import java.io.Serializable;

public class SubscribeResp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int subRespID;
	private int respCode;
	private String desc;
	public int getSubRespID() {
		return subRespID;
	}
	public void setSubRespID(int subRespID) {
		this.subRespID = subRespID;
	}
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "SubscribeResp [subRespID=" + subRespID + ", respCode="
				+ respCode + ", desc=" + desc + "]";
	}
}

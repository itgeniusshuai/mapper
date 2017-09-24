package com.boot.mapper;



public class EssageEntity {

	private Object id;

	private Object content;

	private Object fromUserId;

	private Object toUserId;

	private Object sendTime;

	private Object desc;

	private Object tmp1;

	public void setId(Object id){
		this.id = id;
	}

	public Object getId (Object id){
		return this.id;
	}

	public void setContent(Object content){
		this.content = content;
	}

	public Object getContent (Object content){
		return this.content;
	}

	public void setFromUserId(Object fromUserId){
		this.fromUserId = fromUserId;
	}

	public Object getFromUserId (Object fromUserId){
		return this.fromUserId;
	}

	public void setToUserId(Object toUserId){
		this.toUserId = toUserId;
	}

	public Object getToUserId (Object toUserId){
		return this.toUserId;
	}

	public void setSendTime(Object sendTime){
		this.sendTime = sendTime;
	}

	public Object getSendTime (Object sendTime){
		return this.sendTime;
	}

	public void setDesc(Object desc){
		this.desc = desc;
	}

	public Object getDesc (Object desc){
		return this.desc;
	}

	public void setTmp1(Object tmp1){
		this.tmp1 = tmp1;
	}

	public Object getTmp1 (Object tmp1){
		return this.tmp1;
	}

}
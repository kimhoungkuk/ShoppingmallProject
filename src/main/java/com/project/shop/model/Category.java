package com.project.shop.model;

import java.util.Date;

public class Category {
	
	private long ctgrSeq;
	
	private String ctgrId;
	
	private String prntCtgrId;
	
	private String ctgrName;
	
	private String useYn;
	
	private int dispOrder;
	
	private String ctgrImgUrl;
	
	private String regId;
	
	private Date regDt;
	
	private String modId;
	
	private Date modDt;

	public long getCtgrSeq() {
		return ctgrSeq;
	}

	public void setCtgrSeq(long ctgrSeq) {
		this.ctgrSeq = ctgrSeq;
	}

	public String getCtgrId() {
		return ctgrId;
	}

	public void setCtgrId(String ctgrId) {
		this.ctgrId = ctgrId;
	}

	public String getPrntCtgrId() {
		return prntCtgrId;
	}

	public void setPrntCtgrId(String prntCtgrId) {
		this.prntCtgrId = prntCtgrId;
	}

	public String getCtgrName() {
		return ctgrName;
	}

	public void setCtgrName(String ctgrName) {
		this.ctgrName = ctgrName;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public int getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}

	public String getCtgrImgUrl() {
		return ctgrImgUrl;
	}

	public void setCtgrImgUrl(String ctgrImgUrl) {
		this.ctgrImgUrl = ctgrImgUrl;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}
	
}

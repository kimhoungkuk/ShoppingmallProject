package com.project.shop.model;

import java.util.Date;

/**
 * 카테고리 VO
 * @author ParkSY
 *
 */
public class Category {
	
	/** 카테고리 시퀀스 */
	private long ctgrSeq;
	
	/** 카테고리 ID */
	private String ctgrId;
	
	/** 부모 카테고리 ID */
	private String prntCtgrId;
	
	/** 카테고리 명 */
	private String ctgrName;
	
	/** 사용 유무 */
	private String useYn;
	
	/** 노출 순위 */
	private int dispOrder;
	
	/** 카테고리 이미지 경로 */
	private String ctgrImgUrl = "";
	
	/** 등록자 */
	private String regId;
	
	/** 등록일 */
	private Date regDt;
	
	/** 수정자 */
	private String modId;
	
	/** 수정일 */
	private Date modDt;
	
	/** 수정시 사용 : 변경 될 노출 순위 */
	private int destDispOrder;

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

	public int getDestDispOrder() {
		return destDispOrder;
	}

	public void setDestDispOrder(int destDispOrder) {
		this.destDispOrder = destDispOrder;
	}

}

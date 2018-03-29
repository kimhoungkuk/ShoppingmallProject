package com.project.shop.common;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Paging
 *
 * @author whitelife
 * @since 2014.10.05
 * @version 0.1
 */
public class PageVO {
    private int pageSize; // 게시 글 수
    private int firstPageNo; // 첫 번째 페이지 번호
    private int prevPageNo; // 이전 페이지 번호
    private int startPageNo; // 시작 페이지 (페이징 네비 기준)
    private int pageNo; // 페이지 번호
    private int endPageNo; // 끝 페이지 (페이징 네비 기준)
    private int nextPageNo; // 다음 페이지 번호
    private int finalPageNo; // 마지막 페이지 번호
    private int totalCount; // 게시 글 전체 수
    private int startRownum; // 쿼리 데이터 ROWNUM 시작 숫자
    private int endRownum; // 쿼리 데이터 ROWNUM 종료 숫자
    private String pagegUrl; // 페이지 URL

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the firstPageNo
     */
    public int getFirstPageNo() {
        return firstPageNo;
    }

    /**
     * @param firstPageNo the firstPageNo to set
     */
    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    /**
     * @return the prevPageNo
     */
    public int getPrevPageNo() {
        return prevPageNo;
    }

    /**
     * @param prevPageNo the prevPageNo to set
     */
    public void setPrevPageNo(int prevPageNo) {
        this.prevPageNo = prevPageNo;
    }

    /**
     * @return the startPageNo
     */
    public int getStartPageNo() {
        return startPageNo;
    }

    /**
     * @param startPageNo the startPageNo to set
     */
    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the endPageNo
     */
    public int getEndPageNo() {
        return endPageNo;
    }

    /**
     * @param endPageNo the endPageNo to set
     */
    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }

    /**
     * @return the nextPageNo
     */
    public int getNextPageNo() {
        return nextPageNo;
    }

    /**
     * @param nextPageNo the nextPageNo to set
     */
    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    /**
     * @return the finalPageNo
     */
    public int getFinalPageNo() {
        return finalPageNo;
    }

    /**
     * @param finalPageNo the finalPageNo to set
     */
    public void setFinalPageNo(int finalPageNo) {
        this.finalPageNo = finalPageNo;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    public int getStartRownum() {
		return startRownum;
	}

	public void setStartRownum(int startRownum) {
		this.startRownum = startRownum;
	}

	public int getEndRownum() {
		return endRownum;
	}

	public void setEndRownum(int endRownum) {
		this.endRownum = endRownum;
	}

	public String getPagegUrl() {
		return pagegUrl;
	}

	public void setPagegUrl(String pagegUrl) {
		this.pagegUrl = pagegUrl;
	}

	/**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.makePaging();
    }
    
    public void setPagingUrl(HttpServletRequest request){
    	SortedMap<String, String[]> sMap = Collections.synchronizedSortedMap(new TreeMap<String,String[]>(request.getParameterMap()));
    	
    	StringBuffer parameterStr = new StringBuffer();
    	
    	synchronized (sMap) {
			for(String key : sMap.keySet())
			{
				if(parameterStr != null && parameterStr.length() > 0){
					parameterStr.append("&");
				}
				
				String[] value = sMap.get(key);
				for(int i=0;i<value.length;i++){
					if(!"pageNo".equals(key))
					{
							parameterStr.append(key.toString()+"="+value[i].toString());
					}
				}			
			}
		}

    	String PagingUrl = request.getRequestURL().toString();
    	
    	if(parameterStr != null && parameterStr.length() > 0)
    	{
    		PagingUrl = PagingUrl + "?" + parameterStr.toString() + "&pageNo=";
    	}else{
    		PagingUrl = PagingUrl + "?pageNo=";
    	}

    	this.setPagegUrl(PagingUrl);
    }

    /**
     * 페이징 생성
     */
    private void makePaging() {
        if (this.totalCount == 0) return; // 게시 글 전체 수가 없는 경우
        if (this.pageNo == 0) this.setPageNo(1); // 기본 값 설정
        if (this.pageSize == 0) this.setPageSize(10); // 기본 값 설정

        int finalPage = (totalCount + (pageSize - 1)) / pageSize; // 마지막 페이지
        if (this.pageNo > finalPage) this.setPageNo(finalPage); // 기본 값 설정

        if (this.pageNo < 0 || this.pageNo > finalPage) this.pageNo = 1; // 현재 페이지 유효성 체크

        boolean isNowFirst = pageNo == 1 ? true : false; // 시작 페이지 (전체)
        boolean isNowFinal = pageNo == finalPage ? true : false; // 마지막 페이지 (전체)

        int startPage = ((pageNo - 1) / 10) * 10 + 1; // 시작 페이지 (페이징 네비 기준)
        int endPage = startPage + 10 - 1; // 끝 페이지 (페이징 네비 기준)

        if (endPage > finalPage) { // [마지막 페이지 (페이징 네비 기준) > 마지막 페이지] 보다 큰 경우
            endPage = finalPage;
        }

        this.setFirstPageNo(1); // 첫 번째 페이지 번호

        if (isNowFirst) {
            this.setPrevPageNo(1); // 이전 페이지 번호
        } else {
            this.setPrevPageNo(((pageNo - 1) < 1 ? 1 : (pageNo - 1))); // 이전 페이지 번호
        }

        this.setStartPageNo(startPage); // 시작 페이지 (페이징 네비 기준)
        this.setEndPageNo(endPage); // 끝 페이지 (페이징 네비 기준)

        if (isNowFinal) {
            this.setNextPageNo(finalPage); // 다음 페이지 번호
        } else {
            this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1))); // 다음 페이지 번호
        }

        if(this.pageNo == 1)
        {
        	this.setStartRownum(0);
        }else{
        	this.setStartRownum(this.prevPageNo*this.pageSize);
        }
  
        if(this.pageNo == 1)
        {
        	this.setEndRownum(this.pageSize);
        }else{
            this.setEndRownum(this.nextPageNo*this.pageSize); 
       }
                
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}



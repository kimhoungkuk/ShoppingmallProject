package com.project.shop.service;

import java.util.List;

import com.project.shop.model.Discount;
import com.project.shop.model.Product;
import com.project.shop.model.ProductDiscount;

/**
 * 상품할인
 *
 */
public interface DiscountService {

    /**
     * 상품할인 목록 총 카운트
     * @param criteria
     * @return
     */
	int getDiscountListTotalCount();

    /**
     * 상품할인 목록 
     * @param criteria
     * @return
     */
	List<Discount> getDiscountList(Discount discount);

    /**
     * 상품정보 등록
     * @param Product
     * @return
     */
	int createDiscount(Discount discount);

	/**
	 * 상품할인 정보 
	 * @param prdtCode
	 * @return
	 */
	Discount selectDiscountInfo(int dcntSeq);

	/**
	 * 상품 할인 업데이트 
	 * @param discount
	 * @return
	 */
	int updateDiscount(Discount discount);

	/**
	 * 할인 상품 리스트 등록
	 * @param codeList
	 * @param dcntSeq
	 */
	void saveProductDisCountList(List<String> codeList, int dcntSeq);

	/**
	 * 등록된 상품 리스트 
	 * @param dcntSeq
	 * @return
	 */
	List<Product> getProductList(int dcntSeq);

	/**
	 * 등록된 할인 상품 삭제 
	 * @param dcntSeq
	 * @param prdtCode
	 * @return
	 */
	int deletePrdtDcnt(ProductDiscount productDiscount);

	/**
	 * 상품 할인 삭제 
	 * @param dcntSeq
	 * @return
	 */
	int deleteDiscount(int dcntSeq);
    
}

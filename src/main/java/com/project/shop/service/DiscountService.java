package com.project.shop.service;

import java.util.List;

import com.project.shop.model.Discount;
import com.project.shop.model.Product;

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
    
}

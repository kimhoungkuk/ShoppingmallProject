package com.project.shop.dao;

import java.util.List;

import com.project.shop.model.Discount;
import com.project.shop.model.Product;
import com.project.shop.model.ProductDiscount;

public interface DiscountDao{

    /**
     *  상품할인 목록 총 카운트
     * @param Discount
     * @return
     */
	public int getDiscountListTotalCount();
	
    /**
     * 상품할인 목록 
     * @param criteria
     * @return
     */
	public List<Discount> selectDiscountList(Discount discount);

    /**
     * 상품할인 등록
     * @param Discount
     * @return
     */
	public int createDiscount(Discount discount);

	/**
	 * 상품할인 정보 
	 * @param dcntSeq
	 * @return
	 */
	public Discount selectDiscountInfo(int dcntSeq);

	/**
	 * 상품할인 업데이트 
	 * @param discount
	 * @return
	 */
	public int updateDiscount(Discount discount);

	/**
	 * 매핑 상품 리스트 등록 
	 * @param pd
	 * @return 
	 */
	public int createProductDiscounts(ProductDiscount pd);
	
}

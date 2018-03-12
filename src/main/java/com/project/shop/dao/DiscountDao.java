package com.project.shop.dao;

import java.util.List;

import com.project.shop.model.Discount;

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
	
}

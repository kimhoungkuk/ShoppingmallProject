package com.project.shop.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.service.DiscountService;
import com.project.shop.service.ProductService;
import com.project.shop.dao.DiscountDao;
import com.project.shop.dao.ProductDao;
import com.project.shop.model.Discount;
import com.project.shop.model.Product;

@Service
public class DiscountServiceImpl implements DiscountService{
	
    Log log = LogFactory.getLog(this.getClass());
    
    @Autowired
    private DiscountDao discountDao;

    /**
     * 상품 할인 목록 총 카운트
     */
	@Override
	public int getDiscountListTotalCount() {
		return this.discountDao.getDiscountListTotalCount();
	}

	/**
	 * 상품 할인 목록
	 */
	@Override
	public List<Discount> getDiscountList(Discount discount) {
		return this.discountDao.selectDiscountList(discount);
	}

	@Override
	public int createDiscount(Discount discount) {
		// TODO Auto-generated method stub
		return 0;
	}
    

}

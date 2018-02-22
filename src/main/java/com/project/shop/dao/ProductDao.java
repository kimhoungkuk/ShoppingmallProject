package com.project.shop.dao;

import java.util.List;

import com.project.shop.model.Product;

public interface ProductDao{
	
    /**
     * 상품목록 
     * @param criteria
     * @return
     */
	public List<Product> selectProductList();
	
}

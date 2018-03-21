package com.project.shop.dao;

import java.util.List;
import java.util.Map;

import com.project.shop.model.ProductOption;

/**
 * 상품 옵션 DAO
 * @author SieunLim
 * 
 */

public interface ProductOptionDao {


	public void insertProductOption(ProductOption dto);
	
	public List<ProductOption> selectProductOption();
	
	public ProductOption selectProductOptionModify(String prdtCode);
	
	public void updateProductOption(ProductOption dto);
	
}

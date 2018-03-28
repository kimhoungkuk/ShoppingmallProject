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


	public void insertProductOption(List<ProductOption> list);
	
	public List<ProductOption> selectProductOption();
	
	public List<ProductOption> selectProductOptionModify(String modifydelete);
	
	public void updateProductOption(List<ProductOption> list);
	
}

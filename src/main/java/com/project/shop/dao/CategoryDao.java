package com.project.shop.dao;

import java.util.List;

import com.project.shop.model.Category;

/**
 * @author ParkSY
 * 
 */
public interface CategoryDao {
	
    /**
     * 카테고리 목록
     * @return
     */
	public List<Category> selectCategoryList();
	
}

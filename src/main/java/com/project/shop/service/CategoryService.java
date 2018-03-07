package com.project.shop.service;

import java.util.ArrayList;

import com.project.shop.model.Category;


/**
 * @author ParkSY
 *
 */
public interface CategoryService {

	/**
	 * 카테고리 목록
	 * @parama 
	 * @return
	 */
	public ArrayList<Category> selectCategoryList();
    
}

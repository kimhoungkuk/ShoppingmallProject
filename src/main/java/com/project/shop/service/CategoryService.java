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
	 * @param 
	 * @return
	 */
	public ArrayList<Category> selectCategoryList();
	
	/**
	 * 카테고리 생성
	 * @param category
	 * @return
	 */
	public void insertCategory(Category category);
	
	/**
	 * 카테고리 조회
	 * @param category
	 * @return
	 */
	public Category getCategory(Category category);
	
	/**
	 * 카테고리 수정 (노출 순서 변경, 이름 변경, 사용 유무)
	 * @param type
	 * @param category
	 * @return
	 */
	public int updateCategory(String type, Category category);
    
}

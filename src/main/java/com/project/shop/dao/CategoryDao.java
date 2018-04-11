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
	 * 카테고리 수정 (노출 순서 변경)
	 * @param category
	 * @return
	 */
	public int updateCategoryDispOrder(Category category);

	/**
	 * 카테고리 수정 (이름 변경, 삭제 처리, 사용 유무)
	 * @param category
	 * @return
	 */
	public int updateCategory(Category category);
	
}

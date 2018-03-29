package com.project.shop.dao.ibatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.shop.dao.CategoryDao;
import com.project.shop.model.Category;

/**
 * @author ParkSY
 *
 */
@Repository
public class SqlMapCategoryDao implements CategoryDao {
	
	@Autowired  
    private SqlSession sqlSession; 

    /**
     * 카테고리 목록
     * @return
     */
	@Override
	public List<Category> selectCategoryList() {
		return sqlSession.selectList("CategorySQL.selectCategoryList");
	}
	
	/**
	 * 카테고리 생성
	 * @param category
	 * @return
	 */
	@Override
	public void insertCategory(Category category) {
		sqlSession.insert("CategorySQL.insertCategory", category);
	}
	
	/**
	 * 카테고리 조회
	 * @param category
	 * @return
	 */
	@Override
	public Category getCategory(Category category) {
		return (Category) sqlSession.selectOne("CategorySQL.getCategory", category);
	}

	/**
	 * 카테고리 순서 변경
	 * @param category
	 * @return
	 */
	public int updateCategoryDispOrder(Category category) {
		return sqlSession.update("CategorySQL.updateCategoryDispOrder", category);
	}
	
	/**
	 * 카테고리 수정 (이름 변경, 삭제 처리, 사용 유무)
	 * @param category
	 * @return
	 */
	public int updateCategory(Category category) {
		return sqlSession.update("CategorySQL.updateCategory", category);
	}
	
}

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
	
}

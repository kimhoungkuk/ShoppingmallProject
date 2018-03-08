package com.project.shop.dao.ibatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.shop.dao.ProductImageDao;
import com.project.shop.model.ProductImage;

@Repository //DAO라고 명시 (DAO를 스프링에서 인식시켜줌)
public class SqlMapProductImageDao implements ProductImageDao{
	
	@Autowired  
    private SqlSession sqlSession;

	
	/* 상품 이미지 리스트
	 * (non-Javadoc)
	 * @see com.project.shop.dao.ProductImageDao#selectProductImageList(java.lang.String)
	 */
	@Override
	public List<ProductImage> selectProductImageList(String num) {
		sqlSession.selectList("ProductImageSQL.selectProductImage",num);
		return sqlSession.selectList("ProductImageSQL.selectProductImage",num);
	}

	/* 상품 이미지 등록
	 * (non-Javadoc)
	 * @see com.project.shop.dao.ProductImageDao#insertProductImageDao(com.project.shop.model.ProductImage)
	 */
	@Override
	public void insertProductImageDao(ProductImage dto) {
		sqlSession.insert("ProductImageSQL.insertProductImage", dto);
	} 


	
}

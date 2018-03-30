package com.project.shop.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.shop.dao.ProductDao;
import com.project.shop.model.Product;

@Repository //DAO라고 명시 (DAO를 스프링에서 인식시켜줌)
public class SqlMapProductDao implements ProductDao{
	
	@Autowired  
    private SqlSession sqlSession; 

    /**
     * 상품목록 
     * @param criteria
     * @return
     */
	@Override
	public List<Product> selectProductList(Product product){
		return sqlSession.selectList("ProductSQL.selectProductList",product);
	}
	
	/**
     * 상품목록 
     * @param criteria
     * @return
     */
	@Override
	public List<String> selectProductCodeList(){
		return sqlSession.selectList("ProductSQL.selectProductCodeList");
	}
	
    /**
     * 상품등록
     * @param Product
     * @return
     */
	@Override
	public int getProductListTotalCount(){
		return sqlSession.selectOne("ProductSQL.getProductListTotalCount");
	}
	
    /**
     * 상품등록
     * @param Product
     * @return
     */
	@Override
	public int createProduct(Product product){
		return sqlSession.insert("ProductSQL.createProduct",product);
	}
	
    /**
     * 상품정보
     * @param String prdtCode
     * @return
     */
	@Override
	public Product selectProductInfo(String prdtCode){
		return sqlSession.selectOne("ProductSQL.selectProductInfo",prdtCode);
	}

    /**
     * 상품수정
     * @param product
     * @return
     */
	@Override
	public int updateProduct(Product product){
		return sqlSession.update("ProductSQL.updateProduct",product);
	}
	
}

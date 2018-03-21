package com.project.shop.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.shop.dao.ProductOptionDao;
import com.project.shop.model.ProductOption;

@Repository //DAO라고 명시 (DAO를 스프링에서 인식시켜줌)
public class SqlMapProductOptionDao implements ProductOptionDao{

	@Autowired
	SqlSession sqlsession;

	@Override
	public void insertProductOption(ProductOption dto) {
		sqlsession.insert("ProductOptionSQL.insertProductOption", dto);
	
	}

	@Override
	public List<ProductOption> selectProductOption() {
		return sqlsession.selectList("ProductOptionSQL.selectProductOption");
	}

	@Override
	public ProductOption selectProductOptionModify(String prdtCode) {
		return sqlsession.selectOne("ProductOptionSQL.selectProductOptionModify", prdtCode);
		
	}

	@Override
	public void updateProductOption(ProductOption dto) {
		sqlsession.update("ProductOptionSQL.updateProductOption", dto);
		
	}

	

	
	

	
	
}

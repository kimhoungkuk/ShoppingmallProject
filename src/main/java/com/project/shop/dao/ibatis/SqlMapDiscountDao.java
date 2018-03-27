package com.project.shop.dao.ibatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.shop.dao.DiscountDao;
import com.project.shop.model.Discount;

@Repository
public class SqlMapDiscountDao implements DiscountDao {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 상품 할인 목록 수
	 * 
	 * @return
	 */
	@Override
	public int getDiscountListTotalCount() {
		return sqlSession.selectOne("DiscountSQL.getDiscountListTotalCount");
	}

	/**
	 * 상품 할인 목록
	 */
	@Override
	public List<Discount> selectDiscountList(Discount discount) {
		return sqlSession.selectList("DiscountSQL.selectDiscountList", discount);
	}

	@Override
	public int createDiscount(Discount discount) {
		return sqlSession.insert("DiscountSQL.createDiscount", discount);
	}

	/**
	 *  상품 할인 정보
	 */
	@Override
	public Discount selectDiscountInfo(int dcntSeq) {
		return sqlSession.selectOne("DiscountSQL.selectDiscountInfo",dcntSeq);
	}

}

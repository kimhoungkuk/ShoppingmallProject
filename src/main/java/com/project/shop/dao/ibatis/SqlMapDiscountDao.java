package com.project.shop.dao.ibatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.shop.dao.DiscountDao;
import com.project.shop.model.Discount;
import com.project.shop.model.Product;
import com.project.shop.model.ProductDiscount;

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
	 * 상품 할인 정보
	 */
	@Override
	public Discount selectDiscountInfo(int dcntSeq) {
		return sqlSession.selectOne("DiscountSQL.selectDiscountInfo", dcntSeq);
	}

	/**
	 * 상품 할인 업데이트
	 */
	@Override
	public int updateDiscount(Discount discount) {
		return sqlSession.update("DiscountSQL.updateDiscount", discount);
	}

	/**
	 * 매핑 상품 리스트 등록 
	 * @return 
	 */
	@Override
	public int createProductDiscounts(ProductDiscount productDiscount) {
		return sqlSession.insert("DiscountSQL.createProductDiscounts", productDiscount);
	}

	/**
	 * 등록된 상품 리스트 
	 */
	@Override
	public List<Product> getProductList(int dcntSeq) {
		return sqlSession.selectList("DiscountSQL.selectProductList", dcntSeq);
	}

	/**
	 * 등록된 할인 상품 단일 삭제 
	 */
	@Override
	public int deleteOnePrdtDcnt(ProductDiscount productDiscount) {
		return sqlSession.delete("DiscountSQL.deleteOnePrdtDcnt", productDiscount);
	}

	@Override
	public int deleteDiscount(int dcntSeq) {
		return sqlSession.delete("DiscountSQL.deleteDiscount", dcntSeq);
	}

	/**
	 * 등록된 할인 상품 전체 삭제 
	 */
	@Override
	public int deleteProductDiscount(int dcntSeq) {
		return sqlSession.delete("DiscountSQL.deleteProductDiscount", dcntSeq);
	}

}

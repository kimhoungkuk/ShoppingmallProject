package com.project.shop.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.dao.DiscountDao;
import com.project.shop.model.Discount;
import com.project.shop.model.Product;
import com.project.shop.model.ProductDiscount;
import com.project.shop.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	Log log = LogFactory.getLog(this.getClass());

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

	@Autowired
	private DiscountDao discountDao;

	/**
	 * 상품 할인 목록 총 카운트
	 */
	@Override
	public int getDiscountListTotalCount() {
		return this.discountDao.getDiscountListTotalCount();
	}

	/**
	 * 상품 할인 목록
	 */
	@Override
	public List<Discount> getDiscountList(Discount discount) {
		return this.discountDao.selectDiscountList(discount);
	}

	/**
	 * 상품 할인 등록
	 */
	@Override
	public int createDiscount(Discount discount) {
		try {
			discount.setDcntStartDate(dateFormat.parse(discount.getDcntStartDateStr()));
			discount.setDcntEndDate(dateFormat.parse(discount.getDcntEndDateStr()));
			return this.discountDao.createDiscount(discount);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 상품할인 정보
	 * 
	 * @param prdtCode
	 * @return
	 */
	@Override
	public Discount selectDiscountInfo(int dcntSeq) {
		try {
			return this.discountDao.selectDiscountInfo(dcntSeq);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 상품할인 업데이트
	 */
	@Override
	public int updateDiscount(Discount discount) {
		try {
			discount.setDcntStartDate(dateFormat.parse(discount.getDcntStartDateStr()));
			discount.setDcntEndDate(dateFormat.parse(discount.getDcntEndDateStr()));
			return this.discountDao.updateDiscount(discount);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 할인 상품 리스트 등록
	 */
	@Override
	public void saveProductDisCountList(List<String> codeList, int dcntSeq) {

		List<ProductDiscount> productDiscounts = new ArrayList<>();
		ProductDiscount productDiscount;
		for (String prdtCode : codeList) {
			productDiscount = new ProductDiscount();
			productDiscount.setDcntSeq(dcntSeq);
			productDiscount.setPrdtCode(prdtCode);
			productDiscount.setPrdtDcntRegid("admin");
			productDiscounts.add(productDiscount);
		}
		if (productDiscounts.size() > 0) {
			for(ProductDiscount pd : productDiscounts) {
				this.discountDao.createProductDiscounts(pd);
			}
		}
		
	}

	/**
	 * 등록된 상품 리스트 
	 */
	@Override
	public List<Product> getProductList(int dcntSeq) {
		List<Product> list = this.discountDao.getProductList(dcntSeq);
		return list;
	}

}

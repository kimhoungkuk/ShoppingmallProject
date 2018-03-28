package com.project.shop.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.dao.ProductOptionDao;
import com.project.shop.model.ProductOption;
import com.project.shop.service.ProductOptionService;

@Service
public class ProductOptionServiceImpl implements ProductOptionService {

	Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	ProductOptionDao productOptionDao;

	@Override
	public void registerProductOption(List<ProductOption> list) {
		// TODO Auto-generated method stub
		
				try {
					 this.productOptionDao.insertProductOption(list);
					
				}catch(Exception e) {
					log.error(e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
				
		}
	
	@Override
	public List<ProductOption> getProductOptionList() {
		// TODO Auto-generated method stub
		
			
		
		return this.productOptionDao.selectProductOption();
	}

	@Override
	public List<ProductOption> getProductOptionModify(String modifydelete) {
		// TODO Auto-generated method stub
		try {
			return this.productOptionDao.selectProductOptionModify(modifydelete);
			
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public void updateProductOption(List<ProductOption> list) {
		try {
			this.productOptionDao.updateProductOption(list);
			
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}

	
	}


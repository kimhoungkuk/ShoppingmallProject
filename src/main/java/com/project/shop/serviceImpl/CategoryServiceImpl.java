package com.project.shop.serviceImpl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.dao.CategoryDao;
import com.project.shop.model.Category;
import com.project.shop.service.CategoryService;


/**
 * @author ParkSY
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
    Log log = LogFactory.getLog(this.getClass());
    
    @Autowired
    private CategoryDao categoryDao;
    
    /*
     * (non-Javadoc)
     * @see com.project.shop.service.CategoryService#selectCategoryList()
     */
    @Override
	public ArrayList<Category> selectCategoryList(){
   		return (ArrayList<Category>) categoryDao.selectCategoryList();
	}

}

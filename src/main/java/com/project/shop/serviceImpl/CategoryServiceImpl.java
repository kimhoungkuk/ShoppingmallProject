package com.project.shop.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    
    /**
     * 카테고리 목록
     * @return
     */
    @Override
	public ArrayList<Category> selectCategoryList(){
   		return (ArrayList<Category>) categoryDao.selectCategoryList();
	}
    
    /**
	 * 카테고리 생성
	 * @param category
	 * @return
	 */
    @Override
	public void insertCategory(Category category) {
    	category.setRegId("SYSTEM");
		category.setModId("SYSTEM");
		categoryDao.insertCategory(category);
	}
	
	/**
	 * 카테고리 조회
	 * @param category
	 * @return
	 */
    @Override
	public Category getCategory(Category category) {
		return (Category) categoryDao.getCategory(category);
	}
    
    /**
	 * 카테고리 수정 (노출 순서 변경, 이름 변경, 사용 유무)
	 * @param type
	 * @param category
	 * @return
	 */
	public int updateCategory(String type, Category category) {
    	Category _category = new Category();
    	
    	try {
    		if ("dispOrder".equals(type)) {
    			_category.setCtgrId(category.getCtgrId());
    			_category.setPrntCtgrId(StringUtils.trimAllWhitespace(category.getPrntCtgrId()));
    			_category.setDispOrder(category.getDispOrder());
    			_category.setDestDispOrder(category.getDestDispOrder());
    			return categoryDao.updateCategoryDispOrder(_category);
    			
    		} else if ("rename".equals(type)) {
    			_category.setCtgrId(category.getCtgrId());
				_category.setCtgrName(URLDecoder.decode(StringUtils.trimAllWhitespace(category.getCtgrName()), "UTF-8"));
    			return categoryDao.updateCategory(_category);
    			
    		} else if ("useYn".equals(type)) {
    			_category.setCtgrId(category.getCtgrId());
    			_category.setUseYn(category.getUseYn());
    			return categoryDao.updateCategory(_category);
    			
    		} else {
    			return 0;
    		}
    		
    	} catch (UnsupportedEncodingException uee) {
    		uee.printStackTrace();
    		return 0;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    	
    }
}

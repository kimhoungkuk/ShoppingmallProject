package com.project.shop.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.project.shop.service.ProductImageService;
import com.project.shop.model.ProductImage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 상품 이미지 관리 컨트롤러
 * @author mklee
 *
 */
@Controller 
public class ProductImageController {

	private static final Logger logger = LoggerFactory.getLogger(ProductImageController.class);
	
    @Autowired
    private ProductImageService productImageService;
  

    /**
     * 상품 이미지 등록폼.
     * @param model
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/admin/productImageRegister")
    public ModelAndView productImageForm(Model model) throws SQLException{
    	
    	return new ModelAndView("backoffice/productImage/productImageRegister");    	
    }
    

  	/**상품 이미지 수정폼.
  	 * @param request
  	 * @return
  	 */
  	@RequestMapping("/admin/productImage")
  	@ResponseBody
  	public Map<String,Object> test2(HttpServletRequest request){
  		//폼전송 요소 list
  		String prdtCode = request.getParameter("prdtCode");
  		//String prdtImageCode = request.getParameter("prdtImageCode");
  		
  		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;  //다중파일 업로드
  		//대표이미지 리스트
  		List<MultipartFile> mFiles = multipartRequest.getFiles("input_img");
  		
  		//상세이미지 혹은 대표이미지의 캐러셀 이미지 리스트
  		List<MultipartFile> mFiles2 = multipartRequest.getFiles("input_imgs");
  		
  		productImageService.insertProductImage(request, mFiles, prdtCode);  		
  		productImageService.insertProductImage2(request, mFiles2, prdtCode);
  		
  		String img1=mFiles.get(0).getOriginalFilename();
  		String img2=mFiles.get(1).getOriginalFilename();
  		String img3=mFiles.get(2).getOriginalFilename();
  		
  		String imgs1=mFiles2.get(0).getOriginalFilename();
  		String imgs2=mFiles2.get(1).getOriginalFilename();
  		String imgs3=mFiles2.get(2).getOriginalFilename();
  		String imgs4=mFiles2.get(3).getOriginalFilename();
  		String imgs5=mFiles2.get(4).getOriginalFilename();
  		//map에 담아서 테스트 해보기
  		Map<String,Object> map = new HashMap<>();
  		map.put("prdtCode", prdtCode);
  		//list.put("prdtImageCode", prdtImageCode);
  		map.put("img1", img1);
  		map.put("img2", img2);
  		map.put("img3", img3);
  		map.put("imgs1", imgs1);
  		map.put("imgs2", imgs2);
  		map.put("imgs3", imgs3);
  		map.put("imgs4", imgs4);
  		map.put("imgs5", imgs5);

  		return map;

  	}

  	/**
  	 * 상품 이미지 리스트(상품코드별)
  	 * @param imgnum
  	 * @return
  	 * @throws SQLException
  	 */
  	@RequestMapping(value = "/admin/productImageList/{imgnum}",method=RequestMethod.GET)
  	public ModelAndView productImageList(@PathVariable("imgnum") String imgnum) throws SQLException{  
  		ModelAndView mView=productImageService.getProductImageList(imgnum);
  		mView.setViewName("backoffice/productImage/productImageList");

  		return mView;
  	}

    /**
     * 상품 이미지 수정페이지
     * @param img
     * @param imgnum
     * @return
     * @throws SQLException
     */
    @RequestMapping(value="/admin/productImage/{imgnum}", method=RequestMethod.GET)
    public String updateform(@ModelAttribute ProductImage img, @PathVariable("imgnum") String imgnum) throws SQLException{
    	return null;
    }


    /**
     * 상품 이미지 수정
     * @param img
     * @param imgnum
     * @return
     * @throws SQLException
     */
    @RequestMapping(value="/admin/productImage/{imgnum}", method=RequestMethod.PATCH)
    public String update(@ModelAttribute ProductImage img, @PathVariable int imgnum) throws SQLException{
    	return null;
    }
    

    /**
     * 상품 이미지 삭제
     * @param img
     * @param imgnum
     * @return
     * @throws SQLException
     */
    @RequestMapping(value="/admin/productImage/{imgnum}", method=RequestMethod.DELETE)
    public String delete(@ModelAttribute ProductImage img, @PathVariable int imgnum) throws SQLException{
    	return null;
    }
  
}

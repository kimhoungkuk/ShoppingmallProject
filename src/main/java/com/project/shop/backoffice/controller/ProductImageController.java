package com.project.shop.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.project.shop.service.ProductImageService;
import com.project.shop.model.ProductImage;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 상품 이미지 관리 컨트롤러
 * @author mklee
 *
 */
@Controller
@RequestMapping("/admin/productImage")
public class ProductImageController{

	private static final Logger logger = LoggerFactory.getLogger(ProductImageController.class);
	
    @Autowired
    private ProductImageService productImageService;
  

    /**
     * 상품 이미지 등록폼.(GET)
     * @param model
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/post",method=RequestMethod.GET)
    public ModelAndView productImageForm() throws SQLException{
    	
    	return new ModelAndView("backoffice/productImage/productImageRegister");    	
    }
    

  	/**상품 이미지 등록(POST)
  	 * @param request
  	 * @return
  	 */
  	@RequestMapping(value = "/post", method=RequestMethod.POST)
  	public ModelAndView productImageInsert(HttpServletRequest request){
  		//폼전송 요소 list
  		String prdtCode = request.getParameter("prdtCode");
  		
  		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;  //다중파일 업로드
  		//대표이미지 리스트
  		List<MultipartFile> mFiles = multipartRequest.getFiles("input_img");
  		
  		//상세이미지 혹은 대표이미지의 캐러셀 이미지 리스트
  		List<MultipartFile> mFiles2 = multipartRequest.getFiles("input_imgs");
  		
  		productImageService.ProductImageInsert(request, mFiles);  		
  		productImageService.ProductImageInsert2(request, mFiles2);
  		
  		ModelAndView mView = new ModelAndView();
  		mView.setViewName("redirect:/admin/productImage/"+prdtCode);
  		return mView;

  	}

  	/**
  	 * 상세 상품 이미지(상품코드별)
  	 * @param imgnum
  	 * @return
  	 * @throws SQLException
  	 */
  	@RequestMapping(value = "/{prdtCode}",method=RequestMethod.GET)
  	public ModelAndView productImageDetail(@PathVariable("prdtCode") String prdtCode) throws SQLException{  
  		ModelAndView mView=productImageService.productImageDetail(prdtCode);
  		mView.setViewName("backoffice/productImage/productImageDetail");

  		return mView;
  	}
  	/**상품코드에 따른 삭제
  	 * @param prdtCode
  	 * @param request
  	 * @return
  	 * @throws SQLException
  	 */
  	@RequestMapping(value = "/{prdtCode}",method=RequestMethod.DELETE)
  	public String productImageDelete(@PathVariable("prdtCode") String prdtCode, HttpServletRequest request) throws SQLException{  
  		System.out.println("삭제 전");
  		productImageService.ProductImageDelete(request, prdtCode);  		
  		System.out.println("삭제 후");
  		return "redirect:/admin/productImage/post";
  	}

   
    /**상품 이미지 수정겸 조회페이지
     * @param img
     * @param prdtCode
     * @param request
     * @return
     * @throws SQLException
     */
    @RequestMapping(value="/update/{prdtCode}", method=RequestMethod.POST)
    public String productImageupdate(@PathVariable("prdtCode") String prdtCode, HttpServletRequest request) throws SQLException{    	
    	
    	
  		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;  //다중파일 업로드  		
  		//대표이미지 리스트
  		List<MultipartFile> mFile1 = multipartRequest.getFiles("input_img1");
    	List<MultipartFile> mFile2 = multipartRequest.getFiles("input_img2");
    	List<MultipartFile> mFile3 = multipartRequest.getFiles("input_img3");
    	//상세이미지 리스트
    	List<MultipartFile> mFiles = multipartRequest.getFiles("input_imgs");
    	for(MultipartFile file:mFile1) {
    		System.out.println("진파1"+file.getOriginalFilename());
    		if(!file.isEmpty()) {
    			productImageService.ProductImageUpdate2(multipartRequest, file,"0 ");
    		}
    	}
    	for(MultipartFile file:mFile2) {
    		System.out.println("진파2"+file.getOriginalFilename());
    		if(!file.isEmpty()) {
    			productImageService.ProductImageUpdate2(multipartRequest, file,"1 ");
    		}
    	}
    	for(MultipartFile file:mFile3) {
    		System.out.println("진파3"+file.getOriginalFilename());
    		if(!file.isEmpty()) {
    			productImageService.ProductImageUpdate2(multipartRequest, file,"2 ");
    		}
    	}
    	
    	for(MultipartFile file:mFiles) {
    		System.out.println("진짜 파일 이름2"+file.getOriginalFilename());
    	}
  		
  		if(mFiles !=null) {
  			productImageService.ProductImageUpdate(request, mFiles);
  			
  		}
    	return "redirect:/admin/productImage/"+prdtCode;
    }


}

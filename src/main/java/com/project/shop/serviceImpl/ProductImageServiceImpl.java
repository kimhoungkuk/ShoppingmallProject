package com.project.shop.serviceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.service.ProductImageService;
import com.project.shop.dao.ProductImageDao;
import com.project.shop.model.ProductImage;

@Service
public class ProductImageServiceImpl implements ProductImageService{
	
    Log log = LogFactory.getLog(this.getClass());
    
    @Autowired
    private ProductImageDao productImageDao;


	/* 상품코드별 이미지 출력
	 * (non-Javadoc)
	 * @see com.project.shop.service.ProductImageService#getProductImageList(java.lang.String)
	 */
	@Override
	public ModelAndView productImageDetail(String prdtCode) {
		List<ProductImage> list = productImageDao.selectProductImageList(prdtCode);
		ModelAndView mView=new ModelAndView();
		mView.addObject("list", list);
		for(ProductImage tmp:list) {
			System.out.println(tmp.getPrdtOrgImageName());
		}
		return mView;
	}



	/* 
	 * 메인이미지 세가지 등록 메소드
	 * (non-Javadoc)
	 * @see com.project.shop.service.ProductImageService#insertProductImage(javax.servlet.http.HttpServletRequest, java.util.List)
	 */
	@Override
	public void ProductImageInsert(HttpServletRequest request, List<MultipartFile> mFiles) {
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession()
				.getServletContext().getRealPath("/upload");
		//콘솔에 경로 출력해보기
		System.out.println(realPath);
		
		//MultipartFile 객체의 참조값 얻어오기
		//mFiles 에 담긴 MultipartFile 객체의 참조값을 얻어온다.
		int index = 0;
		for(MultipartFile mFile:mFiles) {
			//원본 파일명
			String orgFileName=mFile.getOriginalFilename();
			//파일 사이즈
			long fileSize=mFile.getSize();
			//저장할 파일의 상세 경로
			String filePath=realPath+File.separator;
			//디렉토리를 만들 파일 객체 생성
			File file=new File(filePath);
			if(!file.exists()){//디렉토리가 존재하지 않는다면
				file.mkdir();//디렉토리를 만든다.
			}
			//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
			String saveFileName=System.currentTimeMillis()+orgFileName;
			try{
				//upload 폴더에 파일을 저장한다.
				mFile.transferTo(new File(filePath+saveFileName));
			}catch(Exception e){
				e.printStackTrace();
			}
			//ImageDto 객체에 추가 정보를 담는다.
			ProductImage dto = new ProductImage();
			
			dto.setPrdtOrgImageName(orgFileName);
			dto.setPrdtSaveImageName(saveFileName);
			dto.setPrdtCode(request.getParameter("prdtCode"));
			//이미지 코드
			dto.setPrdtImageCode(Integer.toString(index));
			System.out.println("next index"+(index++));
			//세션에서 작성자 정보를 얻어온다.
			//String id=(String)request.getSession().getAttribute("id");
			//ImageDto 객체에 작성자 정보를 담는다.
			String path=
					request.getServletContext().getRealPath("/upload")+
					File.separator+dto.getPrdtSaveImageName();
			dto.setPrdtImagePath(path);
			//ImageDao 객체를 이용해서 DB 에 저장하기
			productImageDao.insertProductImageDao(dto);
			
		}
	}


	/* 상세 이미지 등록 메소드
	 * (non-Javadoc)
	 * @see com.project.shop.service.ProductImageService#insertProductImage2(javax.servlet.http.HttpServletRequest, java.util.List)
	 */
	@Override
	public void ProductImageInsert2(HttpServletRequest request, List<MultipartFile> mFiles) {
		// TODO Auto-generated method stub
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession()
				.getServletContext().getRealPath("/upload");
		//콘솔에 경로 출력해보기
		System.out.println(realPath);
		
		//MultipartFile 객체의 참조값 얻어오기
		//mFiles 에 담긴 MultipartFile 객체의 참조값을 얻어온다.
		int index = 5;
		for(MultipartFile mFile:mFiles) {
			//원본 파일명
			String orgFileName=mFile.getOriginalFilename();
			//파일 사이즈
			long fileSize=mFile.getSize();
			//저장할 파일의 상세 경로
			String filePath=realPath+File.separator;
			//디렉토리를 만들 파일 객체 생성
			File file=new File(filePath);
			if(!file.exists()){//디렉토리가 존재하지 않는다면
				file.mkdir();//디렉토리를 만든다.
			}
			//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
			String saveFileName=System.currentTimeMillis()+orgFileName;
			try{
				//upload 폴더에 파일을 저장한다.
				mFile.transferTo(new File(filePath+saveFileName));
			}catch(Exception e){
				e.printStackTrace();
			}
			//ImageDto 객체에 추가 정보를 담는다.
			ProductImage dto = new ProductImage();
			dto.setPrdtOrgImageName(orgFileName);
			dto.setPrdtSaveImageName(saveFileName);
			dto.setPrdtCode(request.getParameter("prdtCode"));
			//이미지 코드
			dto.setPrdtImageCode(Integer.toString(index));
			System.out.println("next index"+(index++));
			//세션에서 작성자 정보를 얻어온다.
			//String id=(String)request.getSession().getAttribute("id");
			//ImageDto 객체에 작성자 정보를 담는다.
			String path=
					request.getServletContext().getRealPath("/upload")+
					File.separator+dto.getPrdtSaveImageName();
			dto.setPrdtImagePath(path);
			
			//ImageDao 객체를 이용해서 DB 에 저장하기
			productImageDao.insertProductImageDao(dto);
		}		
	}


	/* 상품 수정
	 * (non-Javadoc)
	 * @see com.project.shop.service.ProductImageService#ProductImageUpdate(javax.servlet.http.HttpServletRequest, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public void ProductImageUpdate2(HttpServletRequest request, MultipartFile mFile,String prdtImgCode) {

		// 수정할 상품코드의 이미지 삭제하기
		String prdtCode =request.getParameter("prdtCode");
		ProductImage delDto = new ProductImage();
		delDto.setPrdtCode(prdtCode);
		delDto.setPrdtImageCode(prdtImgCode);
		productImageDao.deleteProductImage(delDto);
		System.out.println("update"+prdtImgCode);

		//수정할 상품코드의 이미지 업데이트 하기
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession()
				.getServletContext().getRealPath("/upload");
		//콘솔에 경로 출력해보기
		System.out.println(realPath);
		
		//MultipartFile 객체의 참조값 얻어오기
		//mFiles 에 담긴 MultipartFile 객체의 참조값을 얻어온다.

			System.out.println(mFile.isEmpty());
			//원본 파일명
			String orgFileName=mFile.getOriginalFilename();
			//파일 사이즈
			long fileSize=mFile.getSize();
			//저장할 파일의 상세 경로
			String filePath=realPath+File.separator;
			//디렉토리를 만들 파일 객체 생성
			File file=new File(filePath);
			if(!file.exists()){//디렉토리가 존재하지 않는다면
				file.mkdir();//디렉토리를 만든다.
			}
			//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
			String saveFileName=System.currentTimeMillis()+orgFileName;
			try{
				//upload 폴더에 파일을 저장한다.
				mFile.transferTo(new File(filePath+saveFileName));
			}catch(Exception e){
				e.printStackTrace();
			}
			//ImageDto 객체에 추가 정보를 담는다.
			ProductImage dto = new ProductImage();
			dto.setPrdtOrgImageName(orgFileName);
			dto.setPrdtSaveImageName(saveFileName);
			dto.setFile(mFile);
			dto.setPrdtCode(request.getParameter("prdtCode"));
			//이미지 코드
			dto.setPrdtImageCode(prdtImgCode);
			//세션에서 작성자 정보를 얻어온다.
			//String id=(String)request.getSession().getAttribute("id");
			//ImageDto 객체에 작성자 정보를 담는다.
			String path=
					request.getServletContext().getRealPath("/upload")+
					File.separator+dto.getPrdtSaveImageName();
			dto.setPrdtImagePath(path);
			//ImageDao 객체를 이용해서 DB 에 저장하기
			productImageDao.insertProductImageDao(dto);
		}		


	@Override
	public void ProductImageUpdate(HttpServletRequest request, List<MultipartFile> mFiles) {
		// 수정할 상품코드의 이미지 삭제하기
		String prdtCode =request.getParameter("prdtCode");
		List<ProductImage> list =productImageDao.selectProductImageList2(prdtCode);
		int num=list.size();
		System.out.println("list의 길이는"+num);
		for(int i=5;i<num+5;i++) {			
			ProductImage delDto = new ProductImage();
			delDto.setPrdtCode(prdtCode);
			String sIndex = Integer.toString(i);
			delDto.setPrdtImageCode(sIndex+" ");
			productImageDao.deleteProductImage(delDto);
			System.out.println("update"+num);
		}
		//수정할 상품코드의 이미지 업데이트 하기
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession()
				.getServletContext().getRealPath("/upload");
		//콘솔에 경로 출력해보기
		System.out.println(realPath);
		
		//MultipartFile 객체의 참조값 얻어오기
		//mFiles 에 담긴 MultipartFile 객체의 참조값을 얻어온다.
		int index=5;
		for(MultipartFile mFile:mFiles) {
			System.out.println(mFile.isEmpty());
			//원본 파일명
			String orgFileName=mFile.getOriginalFilename();
			//파일 사이즈
			long fileSize=mFile.getSize();
			//저장할 파일의 상세 경로
			String filePath=realPath+File.separator;
			//디렉토리를 만들 파일 객체 생성
			File file=new File(filePath);
			if(!file.exists()){//디렉토리가 존재하지 않는다면
				file.mkdir();//디렉토리를 만든다.
			}
			//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
			String saveFileName=System.currentTimeMillis()+orgFileName;
			try{
				//upload 폴더에 파일을 저장한다.
				mFile.transferTo(new File(filePath+saveFileName));
			}catch(Exception e){
				e.printStackTrace();
			}
			//ImageDto 객체에 추가 정보를 담는다.
			ProductImage dto = new ProductImage();
			dto.setPrdtOrgImageName(orgFileName);
			dto.setPrdtSaveImageName(saveFileName);
			dto.setPrdtCode(prdtCode);
			//이미지 코드
			dto.setPrdtImageCode(Integer.toString(index));
			System.out.println("next update index"+(index++));
			//세션에서 작성자 정보를 얻어온다.
			//String id=(String)request.getSession().getAttribute("id");
			//ImageDto 객체에 작성자 정보를 담는다.
			String path=
					request.getServletContext().getRealPath("/upload")+
					File.separator+dto.getPrdtSaveImageName();
			dto.setPrdtImagePath(path);
			//ImageDao 객체를 이용해서 DB 에 저장하기
			productImageDao.insertProductImageDao(dto);
		}		
	}



	@Override
	public void ProductImageDelete(HttpServletRequest request, String prdtCode) {
		// TODO Auto-generated method stub
		productImageDao.deleteProductImage(prdtCode);
	}
	

    
}

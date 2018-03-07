-- 상품 마스터 테이블 생성 쿼리


CREATE TABLE SHOP_PRODUCT(
	  PRDT_CODE VARCHAR2(10),
	  PRDT_KOR_NAME VARCHAR2(50) NOT NULL,
	  PRDT_ENG_NAME VARCHAR2(50),
	  PRDT_SELL_PRICE NUMBER NOT NULL,
	  PRDT_BRAND_ID VARCHAR2(10) NOT NULL,
	  PRDT_DISP_YN CHAR(1) NOT NULL,
	  PRDT_DETAIL CLOB NOT NULL,
	  PRDT_REG_ID VARCHAR2(10) NOT NULL,
	  PRDT_REG_DT DATE DEFAULT SYSDATE NOT NULL,
	  PRDT_MOD_ID VARCHAR2(10),
	  PRDT_MOD_DT DATE
);

COMMENT ON TABLE SHOP_PRODUCT IS '쇼핑몰 상품 관리 테이블		';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_CODE IS '상품 코드';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_KOR_NAME IS '상품 한글명';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_ENG_NAME IS '상품 영문명';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_SELL_PRICE IS '상품 판매가';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_BRAND_ID IS '상품 브랜드 아이디';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_DISP_YN IS '상품 전시 여부';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_DETAIL IS '상품 상세 정보';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_REG_ID IS '상품 생성자 아이디';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_REG_DT IS '상품 생성일';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_MOD_ID IS '상품 수정자 아이디';

COMMENT ON COLUMN SHOP_PRODUCT.PRDT_MOD_DT IS '상품 수정일';

ALTER TABLE SHOP_PRODUCT ADD CONSTRAINT XPKSHOP_PRODUCT PRIMARY KEY (PRDT_CODE) ;

-- 사이트 카테고리 관리
-- 카테고리 값 예제 
-- 1DEPTH : 0000 - MEN , 0001 : WOMEN
-- 2DEPTH : 000000 - 상의 , 000001 - 하의 / 000100 - 상의, 000101 - 하의
-- 3DEPTH   00000000 - 티셔츠  , 00000001 - 셔츠  / 00010000 - 티셔츠  , 00010001 - 셔츠 

CREATE TABLE SHOP_CATEGORY(
	  CTGR_SEQ NUMBER(16) NOT NULL,
	  CTGR_ID VARCHAR2(20) NOT NULL,
	  PRNT_CTGR_ID VARCHAR2(20),
	  CTGR_NAME VARCHAR2(30) NOT NULL,
	  USE_YN CHAR(1) NOT NULL,
	  DISP_ORDER NUMBER NOT NULL,
	  CTGR_IMG_URL VARCHAR2(100),
	  REG_ID VARCHAR2(10) NOT NULL,
	  REG_DTM DATE DEFAULT SYSDATE NOT NULL,
	  MOD_ID VARCHAR2(10),
	  MOD_DT DATE
);

COMMENT ON TABLE SHOP_CATEGORY IS '사이트 카테고리 관리 ';

COMMENT ON COLUMN SHOP_CATEGORY.CTGR_ID IS '카테고리 아이디';

COMMENT ON COLUMN SHOP_CATEGORY.PRNT_CTGR_ID IS '부모 카테고리 아이디';

COMMENT ON COLUMN SHOP_CATEGORY.CTGR_NAME IS '카테고리 이름';

COMMENT ON COLUMN SHOP_CATEGORY.USE_YN IS '카테고리 사용 여부';

COMMENT ON COLUMN SHOP_CATEGORY.DISP_ORDER IS '카테고리 전시 순서';

COMMENT ON COLUMN SHOP_CATEGORY.CTGR_IMG_URL IS '카테고리 이미지 URL';

COMMENT ON COLUMN SHOP_CATEGORY.REG_ID IS '카테고리 등록자 아이디';

COMMENT ON COLUMN SHOP_CATEGORY.REG_DTM IS '카테고리 등록일';

COMMENT ON COLUMN SHOP_CATEGORY.MOD_ID IS '카테고리 수정자 아이디';

COMMENT ON COLUMN SHOP_CATEGORY.MOD_DT IS '카테고리 수정일';

-- 상품 카테고리 관리

CREATE TABLE SHOP_PRODUCT_CATEGORY(
	  PRDT_CODE VARCHAR2(10),
	  CTGR_ID VARCHAR2(20),
	  STD_CTGR_YN CHAR(1) NOT NULL
);

COMMENT ON TABLE SHOP_PRODUCT_CATEGORY IS '상품 카테고리 관리 ';

COMMENT ON COLUMN SHOP_PRODUCT_CATEGORY.PRDT_CODE IS '상품 코드';

COMMENT ON COLUMN SHOP_PRODUCT_CATEGORY.CTGR_ID IS '카테고리 아이디';

COMMENT ON COLUMN SHOP_PRODUCT_CATEGORY.STD_CTGR_YN IS '카테고리 사용여부';

ALTER TABLE SHOP_PRODUCT_CATEGORY ADD CONSTRAINT XPKPRODUCT_CATEGORY PRIMARY KEY (PRDT_CODE,CTGR_ID) ;

-- 상품 이미지 관리

CREATE TABLE SHOP_PRODUCT_IMAGE(
	  PRDT_CODE VARCHAR2(10) NOT NULL,
	  PRDT_IMAGE_CODE CHAR(2) NOT NULL,
	  PRDT_IMAGE_PATH VARCHAR2(200) NOT NULL,
	  REG_DTM DATE DEFAULT SYSDATE NOT NULL
);

COMMENT ON TABLE SHOP_PRODUCT_IMAGE IS '상품 이미지 관리';

COMMENT ON COLUMN SHOP_PRODUCT_IMAGE.PRDT_CODE IS '상품 코드';

COMMENT ON COLUMN SHOP_PRODUCT_IMAGE.PRDT_IMAGE_CODE IS '이미지 코드';

COMMENT ON COLUMN SHOP_PRODUCT_IMAGE.PRDT_IMAGE_PATH IS '이미지 URL';

COMMENT ON COLUMN SCOTT.SHOP_PRODUCT_IMAGE.REG_DTM IS '등록 날짜';

-- 사이트 공통 코드 관리

CREATE TABLE SHOP_CODE(
	  CODE_CLASS VARCHAR2(25),
	  CODE_VALUE VARCHAR2(25),
	  CODE_NAME VARCHAR2(100) NOT NULL,
	  CODE_ORDER NUMBER NOT NULL,
	  CODE_DESC VARCHAR2(200),
	  CODE_USE_YN CHAR(1) NOT NULL,
  	  REG_DTM DATE DEFAULT SYSDATE NOT NULL
);

COMMENT ON TABLE SHOP_CODE IS '사이트 코드 관리';

COMMENT ON COLUMN SHOP_CODE.CODE_CLASS IS '코드';

COMMENT ON COLUMN SHOP_CODE.CODE_VALUE IS '코드값';

COMMENT ON COLUMN SHOP_CODE.CODE_NAME IS '코드명';

COMMENT ON COLUMN SHOP_CODE.CODE_ORDER IS '코드순서';

COMMENT ON COLUMN SHOP_CODE.CODE_DESC IS '코드상세설명';

COMMENT ON COLUMN SHOP_CODE.CODE_USE_YN IS '코드사용여부';

COMMENT ON COLUMN SHOP_CODE.REG_DTM IS '등록일';

ALTER TABLE SHOP_CODE ADD CONSTRAINT XPKSHOP_CODE PRIMARY KEY (CODE_CLASS,CODE_VALUE) ;

-- 상품 옵션별 재고 관리

CREATE TABLE SHOP_PRODUCT_OPTION(
  PRDT_CODE VARCHAR2(10) NOT NULL,
  PRDT_COLOR_CODE VARCHAR2(10),
  PRDT_SIZE VARCHAR2(10),
  PRDT_LAVE_COUNT NUMBER(38,0) DEFAULT 0 NOT NULL,
  REGT_DTM DATE DEFAULT SYSDATE NOT NULL,
  RGS_ID VARCHAR2(20) NOT NULL,
  MOD_DTM DATE,
  MOD_ID VARCHAR2(20)
);

COMMENT ON TABLE SHOP_PRODUCT_OPTION IS '상품 옵션별 재고 현황';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.PRDT_CODE IS '상품코드';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.PRDT_COLOR_CODE IS '상품 컬러 코드';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.PRDT_SIZE IS '상품 사이즈';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.PRDT_LAVE_COUNT IS '상품 재고 카운트';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.RGS_ID IS '생성자 아이디';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.REGT_DTM IS '생성일';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.MOD_DTM IS '수정일';

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.MOD_ID IS '수정자 아이디';

alter table SHOP_PRODUCT_OPTION
add (color_name VARCHAR2(20));

COMMENT ON COLUMN SHOP_PRODUCT_OPTION.COLOR_NAME IS '컬러명';

-- 어드민 회원 정보 

CREATE TABLE SHOP_ADMIN_USER(
  USER_ID VARCHAR2(10),
  USER_PASSWORD VARCHAR2(20) NOT NULL,
  USER_NAME VARCHAR2(20) NOT NULL,
  USER_MOBILE VARCHAR2(20),
  USER_ADDRESS VARCHAR2(200),
  USER_ADDRESS_POST_NUM VARCHAR2(10),
  USER_ADDRESS_DETAIL VARCHAR2(200),
  USER_REG_ID VARCHAR2(20) NOT NULL,
  USER_REG_DT DATE DEFAULT SYSDATE NOT NULL,
  USER_MOD_ID VARCHAR2(20),
  USER_MOD_DT DATE,
  USER_LAST_LOGIN_DATE DATE
);

COMMENT ON TABLE SHOP_ADMIN_USER IS '어드민 회원 정보 ';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_ID IS '어드민 회원 아이디';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_PASSWORD IS '어드민 회원 패스워드';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_NAME IS '어드민 회원 이름';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_MOBILE IS '어드민 회원 전화번호';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_ADDRESS IS '어드민 회원 주소';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_ADDRESS_POST_NUM IS '어드민 회원 주소 우편번호';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_ADDRESS_DETAIL IS '어드민 회원 상세주소';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_REG_ID IS '회원정보 생성자 아이디';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_REG_DT IS '회원정보 생성일';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_MOD_ID IS '회원정보 수정자 아이디';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_MOD_DT IS '회원정보 수정일';

COMMENT ON COLUMN SHOP_ADMIN_USER.USER_LAST_LOGIN_DATE IS '마지막 로그인 날짜';

ALTER TABLE SHOP_ADMIN_USER ADD CONSTRAINT XPKSHOP_ADMIN_USER PRIMARY KEY (USER_ID) ;

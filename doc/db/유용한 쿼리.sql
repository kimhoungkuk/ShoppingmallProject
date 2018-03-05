-- 오라클 테이블 컬럼들 카멜케이스 추출 방법 

SELECT COLUMN_NAME, LOWER(SUBSTR(REPLACE(INITCAP(COLUMN_NAME),'_'),1,1))
       ||
       SUBSTR(REPLACE(INITCAP(COLUMN_NAME),'_'),2)
 FROM ALL_TAB_COLS WHERE table_name = 'SHOP_PRODUCT'
 ORDER BY column_id;
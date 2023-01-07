CREATE TABLE PRODUCT 
(
  P_CODE VARCHAR2(20 BYTE) NOT NULL 
, P_NAME VARCHAR2(50 BYTE) NOT NULL 
, REMAIN NUMBER NOT NULL 
, EDIT_DATE VARCHAR2(50 BYTE) NOT NULL); 

insert into product VALUES ('A001', '맨투맨 티', 500, to_char(sysdate+9/24, 'YYYY-MM-DD'));

select * from product;

update product set remain = 20, edit_date = to_char(sysdate+9/24, 'YYYY-MM-DD') where p_code = 'A001'; 

commit;

delete from product where p_code = '8f9fd5c4-7793-4901-a1dc-e698f4995c76';

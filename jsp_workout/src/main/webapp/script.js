/**
 * 
 */

 function signup(){
	 var f = document.signup_form;
	 
	 if(f.user_id == ''){
		 alert ("아이디를 입력해 주십시오.");
		 return false;
	 }
	 if(f.user_pwd == ''){
		 alert ("비밀번호를 입력해 주십시오.");
		 return false;
	 }
	 if(f.user_email == ''){
		 alert ("이메일 주소를 입력해 주십시오.");
		 return false;
	 }
	 
	 alert("회원가입이 완료 되었습니다!");
	 f.submit();
 }
 
 function chk_form(){
	 var f = document.wk_insert;
	 f.submit();
 }
 
 function chk_cancel(){
	 alert("하시던 운동을 취소하시겠습니까?");
	 location.href = "/jsp_workout/main";
 }
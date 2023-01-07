/**
 * 
 */

 function chk_in(){
	 var a = document.formAdd;
	 
	 if(a.p_name == ''){
		alert("상품 이름이 입력되지 않았습니다.");
		return false;
	 }
	 if(a.remain == ''){
		 alert("상품 수량이 입력되지 않았습니다.");
		 return false;
	 }
	 alert("상품 입력이 완료되었습니다!");
	 a.submit();
	 
 }
 
  function chk_ed(){
	 var e = document.formEdit;
	 
	 if(a.p_name == ''){
		alert("상품 이름이 입력되지 않았습니다.");
		return false;
	 }
	 if(a.remain == ''){
		 alert("상품 수량이 입력되지 않았습니다.");
		 return false;
	 }
	 
	 alert("상품 수정이 완료되었습니다!");
	 e.submit();
 }
 
 function chk_delete() {
	const result = confirm("정말 삭제하시겠습니까?");
	if(result){
		alert("삭제가 완료 되었습니다!");
		onsubmit;
	}else{
	return false;		
	}
}
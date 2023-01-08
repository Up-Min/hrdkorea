/**
 * 
 */

 function chk_in(){
	 let a = document.formAdd;
	 
	 if(a.p_name.value == ''){
		alert("상품 이름이 입력되지 않았습니다.");
		return false;
	 }
	 if(a.remain.value == ''){
		 alert("상품 수량이 입력되지 않았습니다.");
		 return false;
	 }
	 alert("상품 입력이 완료되었습니다!");
	 a.submit();
	 
 }
 
  function chk_ed(){
	 var e = document.formEdit;
	 
	 if(e.p_name.value == ''){
		alert("상품 이름이 입력되지 않았습니다.");
		return false;
	 }
	 if(e.remain.value == ''){
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
/**
 * 
 */

 function chk_in(){
	 let a = document.formAdd;
	 let p = a.p_name.value;
	 let r = a.remain.value;
	 
	 if(p == ''){
		alert("상품 이름이 입력되지 않았습니다.");
		return false;
	 }
	 if(r == ''){
		 alert("상품 수량이 입력되지 않았습니다.");
		 return false;
	 }
	 alert("상품 입력이 완료되었습니다!");
	 a.submit();
	 
 }
 
  function chk_ed(){
	 let e = document.formEdit;
	 let p = e.p_name.value;
	 let r = e.remain.value;
	 
	 if(p == ''){
		alert("상품 이름이 입력되지 않았습니다.");
		return false;
	 }
	 if(r == ''){
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
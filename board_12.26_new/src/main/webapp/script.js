
function chkForm(){
	 var f = document.frm;
	 
	 if(f.title.value == ''){
		alert("제목을 입력해 주십시오.");
		return false;
	 }
	 if(f.user_id.value == ''){
		alert("아이디를 입력해 주십시오.");
		return false;
	 }
	 f.submit();
 }
 
 function chkDelete(board_no){
	 const result = confirm("삭제하시겠습니까?");
	 
	if(result){
		const url = location.origin;
		// 앞에 프로토콜과 도메인 주소를 가져오는 origin
		
		location.href = url+"/board_12.26_new/delete?board_no=" + board_no;
		// 페이지 이동.
		 
	}else{
		return false;
	}
 }
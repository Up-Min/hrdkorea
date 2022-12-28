
 // 유효성 체크 먼저 들어가야 합니다. 그리고 포커스가 이동합니다.
function fn_submit(){
	var fn = document.frm; //frm이라 이름붙인 폼을 가져온다.
	
	if(fn.custname.value == ""){
		alert("회원성명이 입력되지 않았습니다."); // input값이 빈 값인지 체크.
		fn.custname.focus(); // 포커싱
		return false; // 함수 끝내기
	}
		if(fn.phone.value == ""){
		alert("회원전화가 입력되지 않았습니다."); // input값이 빈 값인지 체크.
		fn.phone.focus(); // 포커싱
		return false; // 함수 끝내기
	}
		if(fn.address.value == ""){
		alert("회원주소가 입력되지 않았습니다."); // input값이 빈 값인지 체크.
		fn.address.focus(); // 포커싱
		return false; // 함수 끝내기
	}
		if(fn.joindate.value == ""){
		alert("가입일자가 입력되지 않았습니다."); // input값이 빈 값인지 체크.
		fn.joindate.focus(); // 포커싱
		return false; // 함수 끝내기
	}
		if(fn.grade.value == ""){
		alert("고객등급이 입력되지 않았습니다."); // input값이 빈 값인지 체크.
		fn.grade.focus(); // 포커싱
		return false; // 함수 끝내기
	}
		if(fn.city.value == ""){
		alert("도시코드가 입력되지 않았습니다."); // input값이 빈 값인지 체크.
		fn.city.focus(); // 포커싱
		return false; // 함수 끝내기
	}
	// 모두 다 통과했을 시에는 submit을 해줘야한다.
	fn.submit();
}
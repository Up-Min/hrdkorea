
function signup() {
	let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
	var f = document.signup_form;
	var p = f.user_pwd.value;
	var p1 = f.user_pwd1.value;
	var i = f.user_id.value;
	var e = f.user_email.value;

	if (i == '') {
		alert("아이디를 입력해 주십시오.");
		i.focus();
		return false;
	}
	if (p == '') {
		alert("비밀번호를 입력해 주십시오.");
		p.focus();
		return false;
	}
	if (p1 == '') {
		alert("비밀번호 확인을 입력해 주십시오.");
		p1.focus();
		return false;
	}
	if (e == '') {
		alert("이메일 주소를 입력해 주십시오.");
		e.focus();
		return false;
	}

	if (p != p1) {
		alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
		p.focus();
		return false;
	} else if (regex.test(e) == false) {
		alert("올바른 이메일 형식이 아닙니다. 다시 확인해주세요.");
		e.focus();
		return false;
	} else {
		alert("회원가입이 완료 되었습니다.");
		f.submit();
	}
}

/*function pwdchk(){
	var f = document.signup_form;
	var p = f.user_pwd.value;
	var p1 = f.user_pwd1.value;	
	
	
	
	if(p != p1){
		alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.")
	}else if(p = p1){
		alert("비밀번호가 일치합니다!")
	}
}*/

function chkpwd2() {
	var f = document.signup_form;
	var p = f.user_pwd.value;
	var p1 = f.user_pwd1.value;
	var r1 = document.getElementById("label_pwd1");
	var r2 = document.getElementById("label_pwd2");

	r1.style.display = 'none';
	r2.style.display = 'none';

	if (p != p1) {
		r1.style.display = 'none';
		r2.style.display = 'block';
	} else if (p == p1) {
		r1.style.display = 'block';
		r2.style.display = 'none';
	}
}

function chkEmail() {
	let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
	var f = document.signup_form;
	var e = f.user_email.value;
	var e2 = document.getElementById("label_email");
	var e3 = document.getElementById("label_email1");

	e2.style.display = 'none';
	e3.style.display = 'none';

	if (regex.test(e) == false) {
		e2.style.display = 'block';
	}else if (regex.test(e) == true){
		e2.style.display = 'none';
		e3.style.display = 'block';
	}
}


function insert() {
	alert("운동 기록이 완료 되었습니다.");
	submit();
}

function chk_delete() {
	const result = confirm("정말 삭제하시겠습니까?");
	if (result) {
		alert("삭제가 완료 되었습니다.");
		submit();
	}
	return false;
}

function chk_cancel(user_id, user_pwd) {
	var result = confirm("하시던 운동을 취소하시겠습니까?");
	if (result) {
		alert("초기 화면으로 돌아갑니다.");
		location.href = "/jsp_workout/signin?user_id=" + user_id + "&user_pwd=" + user_pwd;
	} else {
		return false;
	}
}

function update() {
	var f = document.editForm;
	var n = f.ex_name.value;
	var w = f.ex_weight.value;
	var r = f.ex_reps.value;
	var s = f.ex_sets.value;

	if (n == '') {
		alert("수정할 운동 이름을 입력해 주십시오.");
		n.focus();
		return false;
	}
	if (w == '') {
		alert("수정할 운동 무게를 입력해 주십시오.");
		w.focus();
		return false;
	}
	if (r == '') {
		alert("수정할 운동 횟수를 입력해 주십시오.");
		r.focus();
		return false;
	}
	if (s == '') {
		alert("수정할 운동 세트를 입력해 주십시오.");
		s.focus();
		return false;
	}

	alert("수정이 완료 되었습니다.");
	submit();
}


function logout() {
	alert("로그아웃이 완료 되었습니다!");
	location.href = "/jsp_workout/";
}



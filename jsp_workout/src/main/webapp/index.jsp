<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 페이지 </title>
<link rel="stylesheet" href="./css/style.css"/>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script type="text/javascript" src = "script.js"></script>
<script>
	function chkpwd2() {
		var p = document.getElementById("pass").value;
		var p1 = document.getElementById("pass1").value;
		var r1 = document.getElementsByClassName("label_pwd1");
		var r2 = document.getElementsByClassName("label_pwd2");

		
		if(p != p1){
 			$("label_pwd1").show;
 			$("label_pwd2").hide;
		}else if(p = p1){
 			$("label_pwd1").hide;
 			$("label_pwd2").show;
		}
	}
</script>
</head>
<body>
<div class="video-background">
    <div class="video-foreground">
     <iframe class="ww" height="605" src="https://www.youtube.com/embed/uz6TUGCXe7c?controls=0&autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </div>
  </div>
<div id="vidtop-content">

<div class="login-wrap">
  <div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">로그인</label>
    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">회원가입</label>
    <div class="login-form">
    	<form name="signin_form" action="signin">
   	<div class="sign-in-htm">
        <div class="group">
          <label for="user" class="label">아이디</label>
          <input id="user" type="text" class="input" name = "user_id">
        </div>
        <div class="group">
          <label for="pass" class="label">비밀번호</label>
          <input id="pass" type="password" class="input" data-type="password" name = "user_pwd">
        </div>
        <div class="group">
          <input type="submit" class="button" value="로그인">
        </div>
      </div>
   		</form>
   		
   	   	<form name="signup_form" action="signup">
   	      <div class="sign-up-htm">
        <div class="group">
          <label for="user" class="label">아이디</label>
          <input id="user" type="text" class="input" name = "user_id" placeholder="15자 이내로 입력해주세요">
        </div>
        <div class="group">
          <label for="pass" class="label">비밀번호</label>
          <input id="pass" type="password" class="input" data-type="password" name = "user_pwd" placeholder="사용할 비밀번호를 입력해주세요">
        </div>
         <div class="group">
          <label for="pass" class="label">비밀번호확인</label>
          <input id="pass1" type="password" class="input" data-type="password" name = "user_pwd1" placeholder="비밀번호를 한번 더 입력해주세요" onchange="chkpwd2()">
        </div>
        <div class="group">
       		<label for="pass" class="label_pwd1" style="display: none;"> 비밀번호가 일치합니다. </label>
       		<label for="pass" class="label_pwd2" style="display: none;"> 비밀번호가 일치하지 않습니다. </label>
        </div>
        <div class="group">
          <label for="pass" class="label">이메일 주소</label>
          <input id="email" type="text" class="input" name = "user_email" placeholder="abc@workout.com 형태로 입력해주세요">
        </div>
        <div class="group">
          <input type="submit" class="button" onclick="signup(); return false;" value="회원가입">
        </div>
      </div>
   		</form>
    </div>
  </div>
</div>
	
</div>

</body>
</html>
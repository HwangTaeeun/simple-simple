<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/www/css/w3.css">
<body>

<div class="w3-container w3-teal">
<h1>Summer Holiday</h1>
<p></p>
</div>

<div class="w3-row-padding w3-margin-top">

<div class="w3-third">
<div class="w3-card">
<img src="/www/img/profile/noimage.png" style="width:50%">
<div class="w3-container">
<h4>5 Terre</h4>
<p></p>
</div>
</div>
</div>

<div class="w3-third">
<div class="w3-card">
<img src="/www/img/profile/noimage.png" style="width:50%">
<div class="w3-container">
<h4>Monterosso</h4>
<p></p>
</div>
</div>
</div>

<div class="w3-third">
<div class="w3-card">
<img src="/www/img/profile/noimage.png" style="width:50%">
<div class="w3-container">
<h4>Vernazza</h4>
<p></p>
</div>
</div>
</div>

</div>
<div class="w3-row-padding w3-margin-top">

<div class="w3-third">
<div class="w3-card">
<img src="/www/img/profile/noimage.png" style="width:50%">
<div class="w3-container">
<h4>Manarola</h4>
<p></p>
</div>
</div>
</div>

<div class="w3-third">
<div class="w3-card">
<img src="/www/img/profile/noimage.png" style="width:50%">
<div class="w3-container">
<h4>Corniglia</h4>
<p></p>
</div>
</div>
</div>

<div class="w3-third">
<div class="w3-card">
<img src="/www/img/profile/noimage.png" style="width:50%">
<div class="w3-container">
<h4>Riomaggiore</h4>
<p></p>
</div>
</div>
</div>

</div>
<c:if test="${empty SID}">
<div class="w3-margin-top w3-margin-right w3-col m1 w3-blue w3-button w3-right" id="goLogin">로그인</div>
</c:if>
<c:if test="${not empty SID}">
<div class="w3-margin-top w3-margin-right w3-col m1 w3-pink w3-button w3-right" id="goWrite">글쓰기</div>
<div class="w3-margin-top w3-margin-right w3-col m1 w3-blue w3-button w3-right" id="goLogout">로그아웃</div>
</c:if>

<script type="text/javascript" src="/www/js/jquery-3.4.1.min.js"></script>
<script>
	$('#goLogin').click(function(){
		$(location).attr('href', '/www/gallery/galleryLogin.van');
	});
	$('#goLogout').click(function(){
		$(location).attr('href','/www/gallery/galleryLogout.van');
	});
	$('#goWrite').click(function(){
		$(location).attr('href','/www/gallery/galleryWriteBoard.van');
	})
</script>
</body>
</html>

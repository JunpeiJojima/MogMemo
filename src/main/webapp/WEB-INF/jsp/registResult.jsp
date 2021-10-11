<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/signUp.css">
</head>
<body>
<h1>Mog Memo</h1>
<div class="login-page">
  <div class="form">
  <c:choose>
			<c:when test="${registResult}">
				<p>SUCCESS!!</p>
				<p> </p>
				<a href="/MogMemo">Sign In</a>
			</c:when>
			<c:otherwise>
				<p>FAILED…</p>
				<p> </p>
				<a href="/MogMemo">Retry</a>
			</c:otherwise>
		</c:choose>
  </div>
</div>	
</body>
</html>
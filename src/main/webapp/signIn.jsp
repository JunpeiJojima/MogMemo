<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/signIn.css">
</head>
<body>
<h1>Mog Memo</h1>
<div class="login-page">
	<c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
	</c:if>
  <div class="form">
    <form class="register-form" action="/MogMemo/RegistrateServlet" method="post">
      <input type="text" placeholder="name" name="userName"/>
      <input type="password" placeholder="password" name="password"/>
      <input type="text" placeholder="email address" name="emailAddress"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form class="login-form" action="/MogMemo/SignInServlet" method="post">
      <input type="text" placeholder="username" name="userName"/>
      <input type="password" placeholder="password" name="passWord"/>
      <button>login</button>
      <p class="message">Not registered? <a href="#">Create an account</a></p>
    </form>
  </div>
</div>	
<script src="${pageContext.request.contextPath}/jquery/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/javascript/signIn.js"></script>
</body>
</html>
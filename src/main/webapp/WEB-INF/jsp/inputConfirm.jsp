<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/signUp.css">
</head>
<body>
<h1>Confirm</h1>
<div class="login-page">
  <div class="form">
    <form class="register-form" action="/MogMemo/CreateAccountServlet" method="post">
      Are these all correct?
      <br>
      <input type="text" name="userName" 
      	value="${registrateEntity.getUserName()}" readonly/>
      <input type="text" name="password" 
      	value="${registrateEntity.getPassWord()}" readonly/>
      <input type="text" name="emailAddress" 
      	value="${registrateEntity.getEmailAddress()}" readonly/>
      <button>OK</button>
      <p class="message"></p><a href="/MogMemo">Go back</a>
    </form>
  </div>
</div>	
</body>
</html>
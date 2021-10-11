<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/upload.css">
</head>
<body>
<h1>Upload</h1>
<div class="login-page">
  <div class="form">
    <form class="register-form" action="${pageContext.request.contextPath}/UploadServlet" 
    	enctype="multipart/form-data" method="post">
		<select class="selectBox" name="category">
		<option value="moning" selected>Moning</option>
		<option value="lunch">Lunch</option>
		<option value="dinner">Dinner</option>
		</select>
      <input type="text" placeholder="Sunny-Side Up" name="cookingName" />
      <textarea class="textareaSize" name="cookingMemo">Memo</textarea>
      <input type="file" name="imageFile"/> 
      <button>Upload</button>
      <p class="message"><a href="/MogMemo/MainPageServlet">Go back</a></p>
    </form>
  </div>
</div>	
<script src="${pageContext.request.contextPath}/jquery/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/javascript/signIn.js"></script>
</body>
</html>
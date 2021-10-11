<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mog Memo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mainPage.css">
</head>
<body>

	<header>
		<div class="wrapper">
			<div class="titleText">MogMemo</div>
		</div>

	</header>

	<div class="accountData">
		<p class="loginAccount">
			Login account: <b><c:out value="${accountEntity.getUserName()}" /></b>
		</p>
		<p class="SignOut">
		<small><a href="/MogMemo/SignoutServlet"> SignOut</a></small>
		</p>
	</div>
	<a href="/MogMemo/upload.jsp" ><button>POSTS</button></a>
	<c:forEach var="mealEntry" items="${mealMap}">
		<p class="postDay">
		<br>
			<c:out value="${mealEntry.key}"/>
		</p>
		<div class="wrapper grid">
			<c:forEach var="mealList" items="${mealEntry.value}">
				<div class="item">
					<img
						src="${pageContext.request.contextPath}/mealDir/${mealList.getImagesPath()}"
						alt="mealImages">
					<p class="textSize">【${mealList.getMealCategory()}】
						${mealList.getCookingName()}</p>
					 &nbsp;${mealList.getCookingMemo()}<br>
					 <c:if test="${mealList.getUserId() == accountEntity.getUserId()}">
							<small><a href="/MogMemo/DeleteMealServlet?mealid=${mealList.getMealId()}">delete</a></small>
					</c:if>
					 
				</div>
			</c:forEach>
		</div>
	</c:forEach>

	<footer>
		<div class="wrapper">
			<p>
				<small>&copy; 2021 MogMemo_Joji</small>
			</p>
		</div>
	</footer>
</body>
</html>
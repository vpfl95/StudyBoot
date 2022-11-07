<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<c:import url="./temp/boot.jsp"></c:import>
	<link href="/css/test.css" rel="stylesheet">
	<script defer src="/js/test.js"></script>
	<!-- defer 병렬처리 js 다운로드 보내고 밑에 처리  -->
</head>
<body>
	<h1>Index page</h1>
	<img src="./images/title1.jpg">
	<a href="./qna/list">QNA</a>
	
	<!-- 로그인 전  -->
	<sec:authorize access="!isAuthenticated()">
		<a href="./member/login">Login</a>
		<a href="/oauth2/authorization/kakao">Kakao Login</a>
		<a href="./member/join">Join</a>
	</sec:authorize>

	<!-- 로그인 성공  -->
	<sec:authorize access="isAuthenticated()"> <!-- 로그인 유무 -->
		<sec:authentication property="Principal" var="user"/>
		<h3><spring:message code="welcome" arguments="${user.name}"></spring:message></h3>
		<h3><spring:message code="welcome2" arguments="${user.id},${user.name}" argumentSeparator=","></spring:message></h3>
		<a href="./member/logout">Logout</a>
		<a href="./member/mypage">My Page</a>
	</sec:authorize>
	
	<sec:authorize url="/admin">	<!-- 권한 이름 -->
		<a href="/admin">Go Admin</a>
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
		<a href="/manager">Go Manger</a>
	</sec:authorize>

	<div>
		<img src="/file/qna/19eaa4af-a0d3-4a0f-b8e0-20af01c68e29_ic_content_star_on_20x20_nor_yellow-7ffe4aa6d3c620c559111070c71a20b7.png" alt="">
		<img src="/file/notice/common.jpg" alt="">
		<a href="/fileDown/qna?fileNum=2">qna down</a>
		<a href="/fileDown/notice?fileNum=2">notice down</a>
	</div>

	<button id="btn">click</button>
	<button class="buttons">BTN1</button>
	<button class="buttons">BTN2</button>
	<button class="buttons">BTN3</button>

	
</body>
</html>
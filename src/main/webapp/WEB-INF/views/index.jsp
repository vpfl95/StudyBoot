<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<c:import url="./temp/boot.jsp"></c:import>
	<link href="/css/test.css" rel="stylesheet">
</head>
<body>
	<h1>Index page</h1>
	<img src="./images/title1.jpg">
	<a href="./qna/list">QNA</a>

	<div>
		<img src="/file/qna/19eaa4af-a0d3-4a0f-b8e0-20af01c68e29_ic_content_star_on_20x20_nor_yellow-7ffe4aa6d3c620c559111070c71a20b7.png" alt="">
		<img src="/file/notice/common.jpg" alt="">
		<a href="/fileDown/qna?fileNum=2">qna down</a>
		<a href="/fileDown/notice?fileNum=2">notice down</a>
	</div>
</body>
</html>
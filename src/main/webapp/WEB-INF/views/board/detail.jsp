<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<h1>Detail Page</h1>
	
	<div>${qnaVO.num}</div>
	<div>${qnaVO.writer}</div>
	<div>${qnaVO.title}</div>
	<div>${qnaVO.contents}</div>
	<div>${qnaVO.regDate}</div>
	<c:forEach items="${qnaVO.files}" var="file">
		<a href="">${file.fileName}</a>
	</c:forEach>
</body>
</html>
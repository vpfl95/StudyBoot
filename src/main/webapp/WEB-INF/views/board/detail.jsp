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
	
	<h3>NUM : ${qnaVO.num}</h3>
	<h3>작성자 : ${qnaVO.writer}</h3>
	<h3>제목 : ${qnaVO.title}</h3>
	<h3>내용 : ${qnaVO.contents}</h3>
	<h3>날짜 : ${qnaVO.regDate}</h3>
	<c:forEach items="${qnaVO.qnaFiles}" var="fileVO">
		<!-- file -> result/upload. -->
		<img  alt="" src="/file/qna/${fileVO.fileName}">  
		<a href="/fileDown/qna?${fileVO.fileNum}">${fileVO.oriName}</a>
	</c:forEach>

	<a href="./update?num=${qnaVO.num}">update</a>
</body>
</html>
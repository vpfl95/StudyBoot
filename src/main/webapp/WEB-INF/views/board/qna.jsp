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
	<h1>List Page</h1>
	
	<table class="table table-hover">
		<tr>
			<th>Num</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
			<th>날짜</th>
		</tr>
		<c:forEach items="${arr}" var="dto">
			<tr>
				<td>${dto.num}</td>
				<td>${dto.writer}</td>
				<td><a href="./detail?num=${dto.num}">${dto.title}</a></td>
				<td>${dto.contents}</td>
				<td>${dto.regDate}</td>
			</tr>
		</c:forEach>
	
	</table>
	
	<div>
		<a href="./write" class="btn btn-primary">Write</a>
	</div>

	<script type="text/javascript">
		let result = '${param.result}';
		if(result != ""){
			if(result =='1'){
				alert('등록성공')
			}else{
				alert('등록실패')
			}
		}
	</script>
	
</body>
</html>
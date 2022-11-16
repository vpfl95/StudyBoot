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
	<h1>Login Page</h1>
	
	<div>
		<h3>${param.error}</h3>
		<h3>${param.message}</h3>
		<h3>${msg}</h3>
	</div>
	
    <form action="./login" method="post">
        <div class="container-fluid">
            <div class="mb-3">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" name="id" value="${cookie.userId.value}" class="form-control" id="inputId">
            </div>
            <div class="mb-3">
                <label for="inputPw" class="form-label">Password</label>
                <input type="password" name="pw" class="form-control" id="inputPw">
            </div>
            <div>
            <!-- 쿠키에 ID넣어서 저장할 수 있음  -->
            	<input type="checkbox" name="rememberId" class="form-check-input" id="exampleCheck1">
            	<label class="form-check-label" for="exampleCheck1">ID 기억하기</label> 
            </div>
            
             <div>
            	<input type="checkbox" name="rememberMe" class="form-check-input" id="exampleCheck2">
            	<label class="form-check-label" for="exampleCheck2">로그인 기억하기</label> 
            </div>
     
            <button type="submit" class="btn btn-primary">로그인</button>
        </div>
    </form>
</body>
</html>
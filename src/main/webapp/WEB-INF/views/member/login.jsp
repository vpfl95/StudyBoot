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
    <form action="./login" method="post">
        <div class="container-fluid">
            <div class="mb-3">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" name="id" class="form-control" id="inputId">
            </div>
            <div class="mb-3">
                <label for="inputPw" class="form-label">Password</label>
                <input type="password" name="pw" class="form-control" id="inputPw">
            </div>
     
            <button type="submit" class="btn btn-primary">로그인</button>
        </div>
    </form>
</body>
</html>
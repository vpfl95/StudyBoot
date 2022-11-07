<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<script defer src="/js/memberJoin.js"></script>
</head>
<body>
	<h1>Join Page</h1>
    
    <form action="./join" method="post" id="joinForm">
        <div class="container-fluid">
            <div class="mb-3">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" name="id" class="form-control" id="inputId">
                <div id="inputIdResult"></div>
                
            </div>
            <div class="mb-3">
                <label for="inputPw" class="form-label">Password</label>
                <input type="password" name="pw" class="form-control" id="inputPw">
                <div id="inputPwResult"></div>
            </div>
            <div class="mb-3">
                <label for="inputPw2" class="form-label">Password</label>
                <input type="password" class="form-control" id="inputPw2">
                <div id="inputPwResult2"></div>
            </div>
            <div class="mb-3">
                <label for="inputName" class="form-label">Name</label>
                <input type="text" name="name" class="form-control" id="inputName">
                <div id="inputNameResult"></div>
            </div>
            <div class="mb-3">
                <label for="inputEmail" class="form-label">Email address</label>
                <input type="email" name="email" class="form-control" id="inputEmail">
                <div id="inputEmailResult"></div>
            </div>

            <div>
                <button type="submit" class="btn btn-primary" id="joinButton">가입</button>
            </div>
        </div>

    </form>

    <!-- 약관 test -->
	<div id="test">
		<div>
			All <input type="checkbox" id="all">
		</div>
        <div>
			동의1 <input type="checkbox" class="check">
            <div>
                약관1
            </div>
		</div>
        <div>
			동의2 <input type="checkbox" class="check">
            <div>
                약관2
            </div>
		</div>
        <div>
			동의3 <input type="checkbox" class="check">
            <div>
                약관3
            </div>
		</div>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../temp/boot.jsp"></c:import>
	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<script defer src="/js/board.js"></script>
	 <!-- include summernote css/js-->
	 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	 <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>
	<h1>Update Page</h1>
	
	<div>
		<form action="update" method="post" enctype="multipart/form-data" style="display: flex; flex-direction: column;">
			<div class="mb-3">
				<label for="writer">작성자</label>
				<input id="writer" value="${vo.writer}" name="writer" type="text">
			</div>
			
			<div class="mb-3"> 
				<label for="title">제목</label>
				<input id="title" value="${vo.title}" name="title" type="text">
			</div>
			
			<div class="mb-3">
				<label for="contents">내용</label>
				<textarea id="contents" name="contents" ></textarea>
			</div>


			<div class="mb-3">
				<c:forEach items="${vo.qnaFiles}" var="fileVO">
					<p>
						${fileVO.oriName}
						<button type="button" class="deleteFile" data-file-num="${fileVO.fileNum}">X</button>
					</p>
				</c:forEach>
			</div>

  
            <div class="mb-3" id="fileAddResult">
                
            </div>
            <div class="mb-3">
                <button type="button" id="fileAdd">FileAdd</button>
            </div>

			
			<div class="mb-3">
				<button class="btn btn-primary" type="submit">Update</button>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		$('#contents').summernote({
			tabsize: 4,
			height: 250
		});

		$("#contents").summernote('code','${vo.contents}')
	</script>
</body>
</html>
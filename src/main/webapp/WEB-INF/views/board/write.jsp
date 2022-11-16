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
	<h1>Board Write Page</h1>
	
	<div>
		<form action="write" method="post" enctype="multipart/form-data" style="display: flex; flex-direction: column;">
			<div class="mb-3">
				<label for="writer">작성자</label>
				<input id="writer" name="writer" type="text">
			</div>
			
			<div class="mb-3"> 
				<label for="title">제목</label>
				<input id="title" name="title" type="text">
			</div>
			
			<div class="mb-3">
				<label for="contents">내용</label>
				<textarea id="contents" name="contents" ></textarea>
			</div>
			

  
            <div class="mb-3" id="fileAddResult">
                
            </div>
            <div class="mb-3">
                <button type="button" id="fileAdd">FileAdd</button>
            </div>

			<!-- <div class="mb-3">
				<label for="contents">File</label>
				<input type="file" name="files">
			</div>
			
			<div class="mb-3">
				<label for="contents">File</label>
				<input type="file" name="files">
			</div> -->
			
			<div class="mb-3">
				<button class="btn btn-primary" type="submit">write</button>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		$('#contents').summernote({
			tabsize:4,
			height:250,
			callbacks: {
				onImageUpload: function(file){
					console.log("imageUpload")
					// ajax로 file sever로 업로드 후 경로를받아서 사용
					uploadFile(file)
				},
				onMediaDelete: function(file){
					console.log("Delete Media");
					console.log("DeleteFile =>",file)
					deleteFile(file)
				}
			}
		});

		function deleteFile(file){
			console.log(file.attr("src"))
			$.post("./summerFileDelete", {fileName:file.attr("src")}, function(result){
				console.log("reuslt ", result)
			})
		}


		//ajax upload 함수
		function uploadFile(file){
			console.log("file ", file)
			console.log("filename =>", file[0].name)
			// <form> form태그
			const formData = new FormData();
			// <input type="file">
			formData.append('file', file[0])

			$.ajax({
				type: "POST",
				url:"summerFile",
				data:formData,
				//header
				cache:false,
				processData:false,
				contentType:false,
				enctype:'multipart/form-data',
				success:function(img){
					console.log("Image =>",img)
					//img = '<img src="' + img + '">'

					$('#contents').summernote('insertImage', img, file[0].name);
				},
				error:function(){
					console.log("이미지 업로드 실패")
				}
			});
		}
		
	</script>
</body>
</html>
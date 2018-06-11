<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 0 auto;
}

form {
	margin: 10px auto;
	width: 80%;
	height: 20px;
	text-align: right;
}

table>tbody>tr:nth-child(4) {
	height: 300px;
	vertical-align: top;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("form input").click(function() {
			if ($(this).attr("class") == "list") {
				//location.href = "list.do";
				
				$("form").attr("action","list.do");
				$("form").submit();
				
			}else if($(this).attr("class") == "reply"){
				$("form").attr("action","writeForm.do");
				$("form").submit();
			}
		})
	});
</script>

</head>
<body>
	<table border="1" width="80%">
		<tr>
			<td width="20%">글쓴이</td>
			<td>${dto.writer}</td>
			<td width="20%">조회수</td>
			<td>${dto.readcount+1}</td>
		</tr>

		<tr>
			<td>제목</td>
			
			<td colspan="3">${dto.subject}</td>
		</tr>

		<tr>
			<td>메일</td>
			<td colspan="3">${dto.email}</td>
		</tr>

		<tr>
			<td>내용</td>

			<td colspan="3">${dto.content }</td>
		</tr>

		<tr>
			<td>파일${dto.re_step}</td>
			<td colspan="3"><a href="download.do?num=${dto.num}">${dto.upload}</a>
			</td>
		</tr>

	</table>

	<form name="frm" method="post">
		<input type="hidden" name="num" value="${dto.num}" />
		<input type="hidden" name="re_step" value="${dto.re_step}" />
		<input type="hidden" name="ref" value="${dto.ref}" />
		<input type="hidden" name="re_level" value="${dto.re_level}" />
		<input type="hidden" name="pageNum" value="${param.page}" />
		<input type="button" value="목록" class="list" /> 
		<input type="button" value="답변" class="reply" /> 
		<input type="button" value="수정" class="update" /> 
		<input type="button" value="삭제" class="del" />
	</form>
</body>
</html>












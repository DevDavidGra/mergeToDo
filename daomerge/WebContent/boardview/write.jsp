<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>

<body>

	<form name="frm" method="post" enctype="multipart/form-data" action="write.do">
	<%-- 답변일 경우 --%>
	<c:if test="${!empty param.num }">
		<input type="hidden" name="num" value="${param.num}" />
		<input type="hidden" name="ref" value="${param.ref}" />
		<input type="hidden" name="re_step" value="${param.re_step}" />
		<input type="hidden" name="re_level" value="${param.re_level}" />
	</c:if>

		<table>
			<tr>
				<td align="right" colspan="2"><a href="list.do">리스트</a></td>
			</tr>

			<tr>
				<td width="20%" align="center">글쓴이</td>
				<td width="80%"><input type="text" name="writer" required /></td>
			</tr>

			<tr>
				<td width="20%" align="center">Email</td>
				<td width="80%"><input type="text" name="email" required /></td>
			</tr>

			<tr>
				<td width="20%" align="center">제목</td>

				<td width="80%">
				<%-- 답변일 경우 --%>
					<c:if test="${param.num!=null}">
							<span>[답변]</span>
					</c:if> 
					<input type="text" name="subject" required />
				</td>
			</tr>

			<tr>
				<td width="20%" align="center">내용</td>
				<td width="80%"><textarea name="content" rows="13" cols="40"
						required></textarea></td>
			</tr>

			<tr>
				<td width="20%" align="center">파일첨부</td>
				<td width="80%" id="fileDiv"><input type="file" name="upload" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="글쓰기" />&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
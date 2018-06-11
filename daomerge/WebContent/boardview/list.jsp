<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td, th {
	text-align: left;
	padding: 2px 10px;
	border: 1px solid #000;
}

th {
	border: 2px solid #000;
}

h1 {
	width: 100%;
	text-align: center;
}

table {
	margin: 0 auto;
}

div.wrap {
	width: 100%;
}
div.wrap > p {
	width:420px;
	height:30px;
	margin:0 auto;
	position:relative;
	
}
div.wrap>p a {
	display: inline-block;
	width: 50px;
	height: 20px;
	position:absolute;
	top:10px; right:0;
	text-decoration:none;

	
}
table td {
	cursor:pointer;
}
div.pager{
	width:400px;
	height:30px;
	margin:0 auto;
	text-align:center;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("table tr").click(function(){
			if($(this).index()==0){
				return;
			}
			var str =$(this).children().eq(0).text();
			console.log(str);
			window.location.href="view.do?num="+str+"&page="+${page};
		});
		$("table tr").hover(function(){
			if($(this).index()==0){
				return;
			}
			$(this).css({
				background:"#ddffd6"
			})
		},function(){
				$(this).css({
					background:"#fff"
				})
		})
	});
</script>

</head>
<body>

	<div class="wrap">
		<h1>게시판 만들기</h1>
		<table>
			<tr>
				<th>num</th>
				<th>subject</th>
				<th>writer</th>
				<th>readcount</th>
				<th>file</th>
			</tr>
			<c:forEach items="${aList}" var="i">

				<tr>
				
					<td>${i.num}</td>
					<td>
					<c:if test="${i.re_level>0}">
						<img src="/jspdemo/boardview/images/level.gif" width="${i.re_level*10}" />
						<img src="/jspdemo/boardview/images/re.gif" />
					</c:if>
					${i.subject}</td>
					<td>${i.writer}</td>
					<td>${i.readcount}</td>
					
					<td>
						<c:if test="${!empty i.upload}">
							<img src="/jspdemo/boardview/images/save.gif" />
						</c:if>
					</td>
				</tr>
			</c:forEach>
			

		</table>
		<div class="pager">
		<c:if test="${pdto.startPage>1}">
			<span><a href="list.do?pageNum=${pdto.startPage-pdto.blockPage}">이전</a></span>
		</c:if>
			<c:forEach begin="${pdto.startPage}" end="${pdto.endPage}" var="j">
				<c:choose>
					<c:when test="${page != j}">
						<span><a href="list.do?pageNum=${j}">${j}</a></span>
					</c:when>
					<c:otherwise>
						<span>${j}</span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pdto.totalPage>pdto.endPage}">
				<span><a href="list.do?pageNum=${pdto.startPage+pdto.blockPage}">다음</a></span>
			</c:if>
		</div>
		<p>
			<a href="writeForm.do">글쓰기</a>
		</p>

	</div>

</body>
</html>



























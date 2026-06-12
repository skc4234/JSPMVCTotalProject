<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- 브라우저 : URL을 이용해서 요청
	브라우저 자체 처리 : JavaScript(유지보수: JQuery, 최근: React/Vue)
	  브라우저
		|
	Controller
		|
	  Model -- @RequestMapping 메소드 호출
	    |---request 받아서 JSP로 전송
	Controller -- JSP로 이동
		|
	   JSP
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px
}
.row {
	margin: 0px auto;
	width: 800px;
}
</style>
</head>
<body>
<div class="container">
	<div class="row text-center">
		<table class="table">
			<tr>	
				<td colspan="2"><img src="${vo.poster }" style="height: 400px" onerror="this.src='no.png'"></td>
			</tr>
			<tr>
				<td width="20%" class="info">장소명</td>
				<td width="80%" class="text-center">${vo.title }</td>
			</tr>
			<c:if test="${vo.hit>0 }">
			<tr>
				<td width="20%" class="info">조회수</td>
				<td width="80%">${vo.hit }</td>
			</tr>
			</c:if>
			
			<tr>
				<td width="20%" class="info">주소</td>
				<td width="80%">${vo.address }</td>
			</tr>
			
			<c:if test="${vo.msg!=null }">
			<tr>
				<td colspan="2">${vo.msg }</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="3" class="text-right">
					<a href="list.do?tno=${tno }" class="btn btn-sm btn-primary">목록</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
	width: 960px;
}
p {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
<div class="container">
	<div class="row text-center">
		<a href="list.do?tno=1" class="btn btn-sm btn-danger">명소</a>
		<a href="list.do?tno=2" class="btn btn-sm btn-success">자연</a>
		<a href="list.do?tno=3" class="btn btn-sm btn-info">쇼핑</a>
		<a href="list.do?tno=4" class="btn btn-sm btn-warning">호텔</a>
	</div>
	<div class="row text-center" style="margin-top: 20px">
		<h1 style="color:red">${title }</h1>
	</div>
	<div class="row text-center" style="margin-top: 20px">
		<c:forEach var="vo" items="${list }">
			<div class="col-sm-3">
				<a href="detail.do?tno=${tno }&no=${vo.no }">
					<div class="thumbnail">
						<img src="${vo.poster }" style="width: 200px; height: 150px; object-fit:cover"
							onerror="this.src='no.png'">
						<p>${vo.title }</p>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="row text-center" style="margin-top: 20px">
		<a href="list.do?tno=${tno }&page=${curPage>1?curPage-1:curPage}" class="btn btn-sm btn-primary">이전</a>
		${curPage } page / ${totalPage } pages 
		<a href="list.do?tno=${tno }&page=${curPage<totalPage?curPage+1:curPage}" class="btn btn-sm btn-primary">다음</a>
	</div>
</div>
</body>
</html>
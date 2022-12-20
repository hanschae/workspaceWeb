<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container{
		width:80%; margin:0 auto;
	}
	.container>img{width:100%}
	
	#top{height:50px; background:pink;}

	#bottom{height:50px; background-color:#ddd;}

</style>
</head>
<body>
<!-- top.jsp include -->
<jsp:include page="top.jsp"></jsp:include>
<div class="container">
	<img src="../img/01.jpg"/>
	<%
		// jsp의 include는 변수를 호환하지 않는다.
		// out.print(name); // 호환안됨, 에러
	%>
</div>
<!-- bottom.jsp include -->
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
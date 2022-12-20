<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// 백엔드 쿠키저장
	// Cookie클래스를 객체로 만들어 response에 를 이용하여 클라이언트 컴퓨터에 저장
	
	Cookie cookie = new Cookie("userid","legochj"); // ("변수","값");
	Cookie cookie2 = new Cookie("nickname","한스채");
	
	// 소멸시점설정
	cookie.setMaxAge(60*3); // 쿠키의 생명주기를 초단위로 설정, 3분의 주기
	
	// 클라이언트에게 전송
	response.addCookie(cookie);
	response.addCookie(cookie2);

%>
</head>
<script>
	document.cookie = "notice=true";
	document.cookie = "test=cookie";
</script>
<body>
<h1><a href="/webJSP/jsp03_cookie/cookieView.jsp">쿠키확인하기</a></h1>
</body>
</html>
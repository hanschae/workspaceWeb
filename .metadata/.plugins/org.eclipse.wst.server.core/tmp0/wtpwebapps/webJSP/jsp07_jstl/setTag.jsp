<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- tag library를 사용하기 위해서는 지시부에 태그라이브러리 정보를 표시하여야 한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>set태그 : 변수선언 및 삭제</h1>
<%
	int a = 123;
	String b = "Rain";
	
	// out.print(a)
%>
<h2>변수선언</h2>
<c:set var="num" value="<%=a %>"/>
<c:set var="no" value="${567}"/>
<c:set var ="name">세종대왕</c:set>
<c:set var="now" value="<%=new Date() %>"/>

<h2>변수사용하기</h2>
<!-- 변수가 없어도 에러는 안남 -->
a = ${c+10}<br/>
num = ${num }<br/>
no = ${no }<br/>
name = ${name }<br/>
now = ${now }

<h2>변수삭제</h2>
<c:remove var="no"/>
no : ${no }
</body>
</html>
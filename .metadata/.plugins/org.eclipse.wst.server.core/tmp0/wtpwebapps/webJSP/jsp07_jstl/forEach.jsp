<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>forEach태그 : 반복문</h1>
<h2>숫자를 이용한 반복문</h2>
<c:set var="dan" value="7"/>
<!-- 시작, 종료, 증가 -->
<c:forEach var="n" begin="2" end="9" step="2">
	${dan } * ${n } = ${dan*n }<br/>
</c:forEach>

<h2>배열을 이용한 반복문</h2>
<%
	int arr[] = {42,34,654,32,34,653,23,43,87};
%>
<c:forEach var="data" items="<%=arr %>">
	[${data }],
</c:forEach>

<h2>컬렉션 : ArrayList를 이용한 반복문</h2>
<%
	List<String> lst = new ArrayList<String>();
	lst.add("홍길동");
	lst.add("세종대왕");
	lst.add("이순신");
	lst.add("장영실");
%>
<c:forEach var="name" items="<%=lst %>">
	[${name}]
</c:forEach>

<h2>Map을 이용한 반복문</h2>
<%
	HashMap<String, String> hm = new HashMap<String, String>();

	hm.put("name","홍길동");
	hm.put("addr","서울시 강남구 역삼동");
	hm.put("tel", "010-8660-5794");
	hm.put("email", "abedc@naver.com");
	
%>
<c:forEach var="mapData" items="<%=hm %>">
	키 : %{mapData.key}, 값 : ${mapData.value}<br/>
</c:forEach>

<h1>forTokens 태그 : 문자열을 특정문자로 조각내기</h1>
<c:forTokens var="t" items="빨강색,파란색,,노란색.보라색.오렌지색-갈색^^초록색-남색" delims=",-.^">
	[[${t }]]
</c:forTokens>

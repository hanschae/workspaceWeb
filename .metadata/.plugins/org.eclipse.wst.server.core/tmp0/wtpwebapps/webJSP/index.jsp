<!-- 지시부 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar, java.io.FileReader" %>
<%@ page import="java.util.Scanner" %>
<%! // 선언부
	// 메소드나 변수를 선언하는 영역
	public String gugudan(int dan){
		String tag = "<ul>";
		for(int i=2; i<=9; i++){
			tag += "<li>"+dan+"*"+i+"="+(dan*i)+"</li>";
		}
		tag +="</ul>";
		return tag;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	header{height:100px; background:lime; color:#fff; line-height:100px; text-align:center; font-size:4em;}
</style>
</head>
<body>
<header>멀캠 홈페이지</header>
<!-- jsp04_session과 같이 볼 것 -->
<% if(session.getAttribute("logStatus")!=null && session.getAttribute("logStatus").equals("Y")){ // 로그인된 경우 %>
	<%=session.getAttribute("logName") %><a href="/webJSP/jsp04_session/sessionLogout.jsp">로그아웃</a>
<% }else{ // 로그인 안된경우 %>
	<a href="<%=request.getContextPath()%>/jsp02_response/login.jsp">로그인</a>
<%} %>
<h2><%=session.getId() %></h2>
<hr/>
	<h2>jstl(jsp standard tag library)</h2>
	<div>
		<p style="background-color=#ddd">
		https://tomcat.apache.org/taglibs/standard에서 다운로드
		jakarta-taglibs-standard-1.1.2.zip 다운로드
		이클립스의 WEB-INF/lib폴더에 jstl.jar, standard.jar를 복사한다.
		</p>
		<ol>
			<li><a href="/webJSP/jsp07_jstl/setTag.jsp">set Tag : 변수를 선언하고 삭제하는 방법</a></li>
			<li><a href="/webJSP/jsp07_jstl/ifTag.jsp?name=이순신&age=500&tel=010-1234-5678">if Tag : 조건문</a>
			<li><a href="/webJSP/jsp07_jstl/forEach.jsp">forEach Tag : 반복문</a>
			<li><a href="/webJSP/jsp07_jstl/url.jsp">url Tag</a></li>
			<li><a href="/webJSP/jsp07_jstl/choose.jsp?name=hong&age=25">choose Tag : 조건문</a></li>
			<li><a href="/webJSP/jsp07_jstl/redirect.jsp">redirect Tag : 자동페이지 이동</a></li>
		</ol>
	</div>

<hr/>
<div>
<%
	// 스크립트릿 : 명령어 입력하는 곳
	int a = 100;
	String name = "홍길동";
	int c = a / 3;
	
	Calendar now = Calendar.getInstance();
	System.out.println("c="+c);
	
	// 내장 객체 : request, response, session, out, application, cookie
	
	out.print("c="+c);

%>
<hr/>
<%
	out.print("<h1>jsp에서 클라이언트에게 보낸 데이터</h1>");

	out.print(gugudan(7));
%>
<hr/>
a=<%=a+15%><br/>
name=<%=name %><br/>
c=<%=c %>
</div>
</body>
</html>
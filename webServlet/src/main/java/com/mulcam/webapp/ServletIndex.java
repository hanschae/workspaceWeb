package com.mulcam.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletIndex")
public class ServletIndex extends HttpServlet {
       
    public ServletIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 홈페이지 구성
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("logId");
		String username = (String)session.getAttribute("logName");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<html>");
		pw.println("<head><title>홈페이지</title></head>");
		pw.println("<body>");
		pw.println("<h1>서블릿 홈페이지 </h1>");
		
		if(userid==null) { // 로그인
			pw.println("<a href='/webServlet/main.do'>로그인</a>");
			pw.close();
		}else { // 로그아웃
			pw.println(username + "님<a href='/webServlet/logout.do'>로그아웃</a>");
			
		}
		pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

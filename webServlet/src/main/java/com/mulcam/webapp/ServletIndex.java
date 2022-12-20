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
		// Ȩ������ ����
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("logId");
		String username = (String)session.getAttribute("logName");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<html>");
		pw.println("<head><title>Ȩ������</title></head>");
		pw.println("<body>");
		pw.println("<h1>���� Ȩ������ </h1>");
		
		if(userid==null) { // �α���
			pw.println("<a href='/webServlet/main.do'>�α���</a>");
			pw.close();
		}else { // �α׾ƿ�
			pw.println(username + "��<a href='/webServlet/logout.do'>�α׾ƿ�</a>");
			
		}
		pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

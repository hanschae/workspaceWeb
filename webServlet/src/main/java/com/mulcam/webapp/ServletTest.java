package com.mulcam.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletTest() {
        super();

    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		/* response.getWriter().append("Served at: ").append(request.getContextPath()); */ 
		System.out.println("doGet()�޼ҵ� �����");
		
		// web.xml�� ���� ServletŬ������ ��������
		
		String userid = getInitParameter("userid");
		System.out.println("userid->"+userid);
		// �α��� ��
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>�α��� ��</title></head><body>");
		pw.print("<h1>�α���</h1><form method='post' action='/webServlet/loginOk.do'/>");
		pw.print("���̵� : <input type='text' name='userid'/><br/>");
		pw.print("��й�ȣ : <input type='password' name='userpwd'/><br/>");
		pw.print("<input type='submit' value='Login'/></form></body></html>");
		pw.close();
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* doGet(request, response); */
		System.out.println("doPost()�޼ҵ� �����");
		// �α���
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		// DB�� ���̵�� ��й�ȣ�� ��ġ�� ��� �̸��� �����Ѵ�.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {		
			// 1. ����̺�ε�
				Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. DB����
			// 	DB �����ּ�, ���̵�, ��й�ȣ
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydb", "root", "root1234");
			
			// 3. Statement����
				String sql = "select username from member where userid=? and userpwd=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpwd);
				
			// 4. ����
				rs = pstmt.executeQuery();
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter pw = response.getWriter();
				if(rs.next()) { // ������ ���ڵ尡 ���� �� : �α��� ����
					// request���� session��ü�� ���Ͽ� �α��� ���θ� ����Ѵ�.
					HttpSession session = request.getSession();
					session.setAttribute("logId", userid);
					session.setAttribute("logName", rs.getString(1));
					
					// ���������� �̵� "Ȩ����"
					pw.println("<script>");
					pw.println("alert('�α��� �����Ͽ����ϴ�. Ȩ�������� �̵��մϴ�.');");
					pw.println("location.href='/webServlet/index.do';");
					pw.println("</script>");
					
				}else { // ������ ���ڵ尡 ���� �� : �α��� ����
					// ���������� �̵� "�ٽ� �α�����������"
					pw.println("<script>");
					pw.println("alert('�α��� �����Ͽ����ϴ�. �α����������� �̵��մϴ�.');");
					pw.println("history.back();");
					pw.println("</script>");
				}
				pw.close();
				
			// 5. �ݱ�
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ee) {}
			
		}
		
	}

}

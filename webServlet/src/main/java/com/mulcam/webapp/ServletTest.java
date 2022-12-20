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
		System.out.println("doGet()메소드 실행됨");
		
		// web.xml의 값을 Servlet클래스로 가져오기
		
		String userid = getInitParameter("userid");
		System.out.println("userid->"+userid);
		// 로그인 폼
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>로그인 폼</title></head><body>");
		pw.print("<h1>로그인</h1><form method='post' action='/webServlet/loginOk.do'/>");
		pw.print("아이디 : <input type='text' name='userid'/><br/>");
		pw.print("비밀번호 : <input type='password' name='userpwd'/><br/>");
		pw.print("<input type='submit' value='Login'/></form></body></html>");
		pw.close();
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* doGet(request, response); */
		System.out.println("doPost()메소드 실행됨");
		// 로그인
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		// DB에 아이디와 비밀번호가 일치할 경우 이름을 선택한다.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {		
			// 1. 드라이브로딩
				Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. DB연결
			// 	DB 서버주소, 아이디, 비밀번호
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydb", "root", "root1234");
			
			// 3. Statement생성
				String sql = "select username from member where userid=? and userpwd=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpwd);
				
			// 4. 실행
				rs = pstmt.executeQuery();
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter pw = response.getWriter();
				if(rs.next()) { // 선택한 레코드가 있을 때 : 로그인 성공
					// request에서 session객체를 구하여 로그인 여부를 등록한다.
					HttpSession session = request.getSession();
					session.setAttribute("logId", userid);
					session.setAttribute("logName", rs.getString(1));
					
					// 웹페이지로 이동 "홈으로"
					pw.println("<script>");
					pw.println("alert('로그인 성공하였습니다. 홈페이지로 이동합니다.');");
					pw.println("location.href='/webServlet/index.do';");
					pw.println("</script>");
					
				}else { // 선택한 레코드가 없을 때 : 로그인 실패
					// 웹페이지로 이동 "다시 로그인페이지로"
					pw.println("<script>");
					pw.println("alert('로그인 실패하였습니다. 로그인페이지로 이동합니다.');");
					pw.println("history.back();");
					pw.println("</script>");
				}
				pw.close();
				
			// 5. 닫기
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

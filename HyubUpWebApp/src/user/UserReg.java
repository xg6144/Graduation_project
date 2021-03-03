package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegChk")
public class UserReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userID = request.getParameter("userID");
		String user_pwd1 = request.getParameter("user_pwd1");
		String user_pwd2 = request.getParameter("user_pwd2");
		String user_name = request.getParameter("user_name");
		String user_age = request.getParameter("user_age");
		String user_gender = request.getParameter("user_gender");
		String user_email = request.getParameter("user_email");
		String user_profile = request.getParameter("user_profile");
		
		if(userID == null || userID.equals("") || user_pwd1 == null || user_pwd1.equals("") ||
				user_pwd2 == null || user_pwd2.equals("") || user_name == null || user_name.equals("")
				|| user_age == null || user_age.equals("") || user_gender == null || user_gender.equals("") || user_email == null || user_email.equals("")) {
			request.getSession().setAttribute("messageType", "Error");
			request.getSession().setAttribute("messageType", "Enter Error Message");
			response.sendRedirect("join/join.jsp");
			return;
		}
		if(!user_pwd1.equals(user_pwd2)) {
			request.getSession().setAttribute("messageType", "Error");
			request.getSession().setAttribute("messageType", "비밀번호가 다릅니다.");
			response.sendRedirect("join/join.jsp");
		}
		
		int result = new UserDAO().register(userID, user_pwd1, user_profile, user_name, user_age, user_gender, user_email);
		
		if(result == 1) {
			request.getSession().setAttribute("messageType", "성공");
			request.getSession().setAttribute("messageType", "회원가입에 성공했습니다.");
			response.sendRedirect("index.jsp");
		}
		else {
			request.getSession().setAttribute("messageType", "Error");
			request.getSession().setAttribute("messageType", "이미 존재하는 회원입니다.");
			response.sendRedirect("join/join.jsp");
		}
	}

}

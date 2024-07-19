package com.letslearn.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letslearn.DBcon.DbCon;
import com.letslearn.Dao.UserDao;
import com.letslearn.Modal.User;

@WebServlet("/UserloginServlet")
public class UserloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dis = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailString = request.getParameter("email");
		String passwordString= request.getParameter("password");
		
		try {
			//for user based login credentials
			UserDao adao = new UserDao(DbCon.getConnection());
			User user = adao.userLogin(emailString, passwordString);
			if(user!=null) {
				request.getSession().setAttribute("authUser", user);
				//redirects to index.jsp page
				request.setAttribute("status", "success");
				dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
			}else {
				//redirects to index.jsp page
				request.setAttribute("status", "error");
				dis = request.getRequestDispatcher("auth-login-user.jsp");
				dis.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

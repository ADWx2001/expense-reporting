package com.letslearn.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letslearn.DBcon.DbCon;
import com.letslearn.Dao.WelfareDao;
import com.letslearn.Modal.Welfare;

@WebServlet("/WelfareServlet")
public class WelfareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dis = null;
	String action = null;
	String company;
	String nic;
	String connection;
	String email;
	String telephone;
	String id;

	private int result = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		action = request.getParameter("action");
		if (action.equals("delete")) {
			id = request.getParameter("id");

			try {
				WelfareDao welfareDao = new WelfareDao(DbCon.getConnection());
				result = welfareDao.deleteWelfare(id);
				if (result != 0) {
					response.sendRedirect("welfareManagement.jsp");

				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error . Please try again.');</script>");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		action = request.getParameter("action");
		
		company = request.getParameter("company");
		nic = request.getParameter("nic");
		connection = request.getParameter("connection");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		id = request.getParameter("id");
		
		
		if(action.equals("addWelfare")) {
			try {
				Welfare welfare = new Welfare("", email, nic,(id), company, connection, telephone);
				WelfareDao welfareDao = new WelfareDao(DbCon.getConnection());
				result = welfareDao.addWelfare(welfare);
				if (result != 0) {
					response.sendRedirect("welfareManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("update")){
			try {
				Welfare welfare = new Welfare("", email, nic,(id), company, connection, telephone);
				WelfareDao welfareDao = new WelfareDao(DbCon.getConnection());
				result = welfareDao.updateWelfare(welfare);
				if (result != 0) {
					response.sendRedirect("welfareManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error  Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

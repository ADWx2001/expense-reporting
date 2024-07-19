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
import com.letslearn.Dao.nAdminDao;
import com.letslearn.Modal.nAdmin;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dis = null;
	String action = null;
	String adminName;
	String nic;
	String department;
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
				nAdminDao welfareDao = new nAdminDao(DbCon.getConnection());
				result = welfareDao.deleteAdmin(id);
				if (result != 0) {
					response.sendRedirect("adminManagement.jsp");

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
		
		adminName = request.getParameter("adminName");
		nic = request.getParameter("nic");
		department = request.getParameter("department");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		id = request.getParameter("id");
		
		
		if(action.equals("addAdmin")) {
			try {
				nAdmin nadmin = new nAdmin("", email, nic,(id), adminName, department, telephone);
				nAdminDao nadminDao = new nAdminDao(DbCon.getConnection());
				result = nadminDao.addAdmins(nadmin);
				if (result != 0) {
					response.sendRedirect("adminManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("update")){
			try {
				nAdmin nadmin = new nAdmin("", email, nic,(id), adminName, department, telephone);
				nAdminDao nadminDao = new nAdminDao(DbCon.getConnection());
				result = nadminDao.updateAdmin(nadmin);
				if (result != 0) {
					response.sendRedirect("adminManagement.jsp");
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

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
import com.letslearn.Dao.ComplainDao;
import com.letslearn.Modal.Complaint;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int result = 0;
	RequestDispatcher dis = null;
	String action = null;
	String nic = null;
	String typeComp = null;
	String tel = null;
	String about = null;
	String fname = null;
	String emailString = null;
	String id;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		action = request.getParameter("action");
		if(action.equals("delete")){
			emailString = request.getParameter("email");
			nic = request.getParameter("nic");
			typeComp = request.getParameter("typeComp");
			tel = request.getParameter("tel");
			about = request.getParameter("about");
			fname = request.getParameter("fname");
			id = request.getParameter("id");
			
			try {
				ComplainDao hrDao = new ComplainDao(DbCon.getConnection());
				result = hrDao.deleteComplaint(id);
				if (result != 0) {
					response.sendRedirect("complaint.jsp");
					
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
		
		emailString = request.getParameter("email");
		nic = request.getParameter("nic");
		typeComp = request.getParameter("typeComp");
		tel = request.getParameter("tel");
		about = request.getParameter("about");
		fname = request.getParameter("fname");
		id = request.getParameter("id");
		
		if(action.equals("addComplaint")) {
			try {
				Complaint complaint = new Complaint(fname, emailString,nic,id ,tel, typeComp, about);
				ComplainDao hrDao = new ComplainDao(DbCon.getConnection());
				result = hrDao.addComplaint(complaint);
				if (result != 0) {
					response.sendRedirect("complaint.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("update")){
			try {
				System.out.println(id);
				Complaint complaint = new Complaint(fname, emailString,nic,id ,tel, typeComp, about);
				ComplainDao hrDao = new ComplainDao(DbCon.getConnection());
				result = hrDao.updateComplaint(complaint);
				if (result != 0) {
					response.sendRedirect("complaint.jsp");
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

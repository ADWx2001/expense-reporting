<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.letslearn.Modal.Expense" %> 
<%@ page import="com.letslearn.DBcon.DbCon" %> 
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import=" java.sql.Connection" %>
    
<%
	if (session.getAttribute("auth") == null) {
	    response.sendRedirect("login.jsp");
	}
	
	Double lastCollectionTotal = (Double) request.getAttribute("lastCollectionTotal");
	Double totalLast30Days = (Double) request.getAttribute("totalLast30Days");
	Double totalLast365Days = (Double) request.getAttribute("totalLast365Days");
	List<Expense> expenses = (List<Expense>) request.getAttribute("allexpenses");
%>


<!DOCTYPE html>
<html>
<head>
	<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
	<meta charset="UTF-8">
	<title>JukeBox Expense Manage</title>
</head>
<body>
	<div class="container mt-5 pb-5">
        <h1>Juke Box expense manage</h1>
        <div class="d-flex justify-content-between mb-4">
            <a href="main.jsp" class="btn btn-primary">Back to Main</a>
            <a href="reporting.jsp" class="btn btn-primary">Reporting</a>
        </div>
        <div>
            <h3>Last collection total value: <span id="lastCollectionTotal"><%= lastCollectionTotal %></span></h3>
            <h3>Total value for Last 30 Days: <span id="last30DaysTotal"><%= totalLast30Days %></span></h3>
            <h3>Total value for Last 365 Days: <span id="last365DaysTotal"><%= totalLast365Days %></span></h3>
        </div>
        
       <br>
       <br> 
        
        
     <table class="table">
  <thead>
    <tr>
      <th scope="col">Date</th>
      <th scope="col">Amount</th>
      <th scope="col">Machine</th>
      <th scope="col">Reason</th>
        <th scope="col">Action</th>
       <th scope="col">Remove</th>
      
      
    </tr>
  </thead>
  <tbody>
   
 <c:forEach var="expenses" items="${expenses}">
            <tr>
            
                <td>${expenses.date}</td>
                <td>${expenses.amount}</td>
                <td>${expenses.machine}</td>
                <td>${expenses.reason}</td>
                <td>${expenses.expenseAction}</td>
                  <td>Remove</td>
            </tr>
        </c:forEach>
    
    
  </tbody>
</table>
	</div>
</body>
</html>
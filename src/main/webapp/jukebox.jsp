<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.letslearn.Modal.Expense" %> 

<%
if (session.getAttribute("auth") == null) {
    response.sendRedirect("login.jsp");
}

Double lastCollectionTotal = (Double) request.getAttribute("lastCollectionTotal");
Double totalLast30Days = (Double) request.getAttribute("totalLast30Days");
Double totalLast365Days = (Double) request.getAttribute("totalLast365Days");
List<Expense> expenses = (List<Expense>) request.getAttribute("expenses");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Juke Box</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Juke Box</h1>
        <div class="d-flex justify-content-between mb-4">
            <a href="main.jsp" class="btn btn-primary">Back to Main</a>
            <a href="reporting.jsp" class="btn btn-primary">Reporting</a>
        </div>
        <div>
            <h3>Last Collection Total: <span id="lastCollectionTotal"><%= lastCollectionTotal %></span></h3>
            <h3>Total for Last 30 Days: <span id="last30DaysTotal"><%= totalLast30Days %></span></h3>
            <h3>Total for Last 365 Days: <span id="last365DaysTotal"><%= totalLast365Days %></span></h3>
        </div>
        <div class="row">
            <div class="col-md-8">
                <form action="JukeBoxServlet" method="POST" class="mt-4">
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="date" class="form-control" id="date" name="date" required>
                    </div>
                    <div class="form-group">
                        <label for="amount">Amount</label>
                        <input type="number" class="form-control" id="amount" name="amount" required>
                    </div>
                    <button type="submit" class="btn btn-success mt-2">Save</button>
                </form>
                <form action="JukeBoxServlet" method="POST" class="mt-4">
                    <div class="form-group">
                        <label for="expenseDate">Expense Date</label>
                        <input type="date" class="form-control" id="expenseDate" name="expenseDate" required>
                    </div>
                    <div class="form-group">
                        <label for="expenseAmount">Expense Amount</label>
                        <input type="number" class="form-control" id="expenseAmount" name="expenseAmount" required>
                    </div>
                    <button type="submit" class="btn btn-danger mt-2">Add Expense</button>
                </form>
            </div>
            
        </div>
    </div>
</body>
</html>

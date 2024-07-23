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
    <title>Fruit Machine</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
    <div class="container mt-5 mb-10">
        <h1>Fruit Machine</h1>
        <div class="d-flex justify-content-between mb-4">
            <a href="main.jsp" class="btn btn-primary">Back to Main</a>
            <a href="reporting.jsp" class="btn btn-primary">Reporting</a>
        </div>
        <div>
           	<h3>Last collection total value: <span id="lastCollectionTotal"><%= lastCollectionTotal %></span></h3>
            <h3>Total value for Last 30 Days: <span id="last30DaysTotal"><%= totalLast30Days %></span></h3>
            <h3>Total value for Last 365 Days: <span id="last365DaysTotal"><%= totalLast365Days %></span></h3>
        </div>
        <div class="row">
             <div class="d-flex justify-content-start">
            	<div class="col-5 ">
            	<h3 class="mt-5">Add collection to fruit machine</h3>
	            	<form action="FruitMachineServlet" method="POST" class="mt-4">
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
            	</div>
            	<div class="col-1"></div>
            	<div class="col-5">
            	<h3 class="mt-5">Add expence to fruit machine</h3>
            		<form action="FruitMachineServlet" method="POST" class="mt-4">
					    <div class="form-group">
					        <label for="expenseDate">Expense Date</label>
					        <input type="date" class="form-control" id="expenseDate" name="expenseDate" required>
					    </div>
					    <div class="form-group">
					        <label for="expenseAmount">Expense Amount</label>
					        <input type="number" class="form-control" id="expenseAmount" name="expenseAmount" required oninput="validateExpenseAmount()">
					        <div class="invalid-feedback" id="expenseAmountError">Expense amount must be negative.</div>
					    </div>
					    <div class="form-group">
					        <label for="expenseType">Expense Type</label>
					        <select class="form-control" id="expenseAction" name="expenseAction" required>
					            <option value="" disabled selected>Please select one</option>
					            <option value="addExpense">Add Expense</option>
					            
					        </select>
					    </div>
					    <div class="form-group">
					        <label for="expenseReason">Expense Reason</label>
					        <input type="text" class="form-control" id="expenseReason" name="expenseReason" required>
					    </div>
					    <div class="form-group" >
					        <input type="text" class="form-control" id="expenseMachine" name="expenseMachine" value="jukebox" hidden>
					    </div>
					    <button type="submit" class="btn btn-danger mt-2">Submit</button>
					</form>
						<a href="fruitMachineExpenseManage.jsp">
					<button type="submit" class="btn btn-primary mt-2">Manage Expenses</button>
					</a>
					
					<script>
						function validateExpenseAmount() {
						    const expenseAmount = document.getElementById('expenseAmount');
						    const expenseAmountError = document.getElementById('expenseAmountError');
						    
						    if (expenseAmount.value >= 0) {
						        expenseAmount.classList.add('is-invalid');
						        expenseAmountError.style.display = 'block';
						    } else {
						        expenseAmount.classList.remove('is-invalid');
						        expenseAmountError.style.display = 'none';
						    }
						}
						</script>
            	</div>
                
                
            </div>
            
        </div>
    </div>
</body>
</html>

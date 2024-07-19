<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
if (session.getAttribute("auth") == null) {
    response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reporting</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Reporting</h1>
        <div class="d-flex justify-content-between mb-4">
            <a href="main.jsp" class="btn btn-primary">Back to Main</a>
        </div>
        <form action="ReportServlet" method="POST">
            <div class="form-group">
                <label for="machine">Select Machine</label>
                <select class="form-control" id="machine" name="machine" required>
                    <option value="jukebox">Juke Box</option>
                    <option value="pooltable">Pool Table</option>
                    <option value="fruitmachine">Fruit Machine</option>
                    <option value="tubz">Tubz</option>
                </select>
            </div>
            <div class="form-group">
                <label for="startDate">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Generate Report</button>
        </form>
        <div id="reportResults" class="mt-5">
            <!-- Display the report results here -->
        </div>
    </div>
</body>
</html>

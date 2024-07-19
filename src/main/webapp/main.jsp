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
    <title>Main Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Main Page</h1>
        <div class="d-flex flex-column">
            <a href="jukebox.jsp" class="btn btn-primary mb-3">Juke Box</a>
            <a href="pooltable.jsp" class="btn btn-primary mb-3">Pool Table</a>
            <a href="fruitmachine.jsp" class="btn btn-primary mb-3">Fruit Machine</a>
            <a href="tubz.jsp" class="btn btn-primary mb-3">Tubz</a>
            <a href="reporting.jsp" class="btn btn-primary mb-3">Reporting</a>
        </div>
    </div>
</body>
</html>

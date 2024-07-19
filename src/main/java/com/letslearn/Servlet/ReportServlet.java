package com.letslearn.Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String machine = request.getParameter("machine");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Here you would handle the logic to fetch the report data from the database
        // Example:
        // List<ReportEntry> reportEntries = reportDAO.getReportEntries(machine, startDate, endDate);

        // request.setAttribute("reportEntries", reportEntries);

        RequestDispatcher rd = request.getRequestDispatcher("reporting.jsp");
        rd.forward(request, response);
    }
}

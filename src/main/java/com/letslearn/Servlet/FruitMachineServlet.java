package com.letslearn.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letslearn.Dao.CollectionDAOImpl;
import com.letslearn.Dao.ExpenseDAOImpl;
import com.letslearn.Interface.CollectionDAO;
import com.letslearn.Interface.ExpenseDAO;
import com.letslearn.Modal.Collection;
import com.letslearn.Modal.Expense;

/**
 * Servlet implementation class FruitMachineServlet
 */
@WebServlet("/FruitMachineServlet")
public class FruitMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/fiverdb";
        String dbUser = "root";
        String dbPassword = "1111";
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    }
	
	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection()) {
            CollectionDAO collectionDAO = new CollectionDAOImpl(connection);
            double lastCollectionTotal = collectionDAO.getLastCollectionTotal("fruitmachine");
            double totalLast30Days = collectionDAO.getTotalLast30Days("fruitmachine");
            double totalLast365Days = collectionDAO.getTotalLast365Days("fruitmachine");

            ExpenseDAO expenseDAO = new ExpenseDAOImpl(connection);
            List<Expense> expenses = expenseDAO.getExpenses("fruitmachine", "2023-01-01", "2023-12-31");

            request.setAttribute("lastCollectionTotal", lastCollectionTotal);
            request.setAttribute("totalLast30Days", totalLast30Days);
            request.setAttribute("totalLast365Days", totalLast365Days);
            request.setAttribute("expenses", expenses);

            RequestDispatcher dispatcher = request.getRequestDispatcher("fruitmachine.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String date = request.getParameter("date");
        String amount = request.getParameter("amount");
        String expenseDate = request.getParameter("expenseDate");
        String expenseAmount = request.getParameter("expenseAmount");
        String expenseReason = request.getParameter("expenseReason");
        String expenseAction = request.getParameter("expenseAction");
        String expenseMachine = request.getParameter("expenseMachine");

        try (Connection connection = getConnection()) {
            if (date != null && amount != null) {
                Collection collection = new Collection();
                collection.setDate(date);
                collection.setAmount(Double.parseDouble(amount));
                collection.setMachine("fruitmachine");

                CollectionDAO collectionDAO = new CollectionDAOImpl(connection);
                collectionDAO.saveCollection(collection);
            } else if (expenseDate != null && expenseAmount != null && expenseReason != null && expenseAction != null && expenseAction.equals("addExpense")) {
                Expense expense = new Expense();
                expense.setDate(expenseDate);
                expense.setAmount(Double.parseDouble(expenseAmount));
                expense.setMachine("fruitmachine");
                expense.setReason(expenseReason);
                expense.setAction(expenseAction);

                ExpenseDAO expenseDAO = new ExpenseDAOImpl(connection);
                expenseDAO.saveExpense(expense);
            }else if (expenseDate != null && expenseAmount != null && expenseAction.equals("removeExpense")) {
            	ExpenseDAO expenseDAO = new ExpenseDAOImpl(connection);
                expenseDAO.removeExpense(expenseDate, expenseAmount, expenseAction, expenseMachine);;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }

        response.sendRedirect("FruitMachineServlet");
    }
    

}

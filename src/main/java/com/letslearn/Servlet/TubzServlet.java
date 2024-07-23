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
 * Servlet implementation class TubzServlet
 */
@WebServlet("/tubzServlet")
public class TubzServlet extends HttpServlet {
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
            double lastCollectionTotalTofee = collectionDAO.getLastCollectionTotal("tubzTofee");
            double totalLast30DaysTofee = collectionDAO.getTotalLast30Days("tubzTofee");
            double totalLast365DaysTofee = collectionDAO.getTotalLast365Days("tubzTofee");
            
            double lastCollectionTotalToys = collectionDAO.getLastCollectionTotal("tubzToys");
            double totalLast30DaysToys = collectionDAO.getTotalLast30Days("tubzToys");
            double totalLast365DaysToys = collectionDAO.getTotalLast365Days("tubzToys");

            ExpenseDAO expenseDAO = new ExpenseDAOImpl(connection);
            List<Expense> expensesTofee = expenseDAO.getExpenses("tubzTofee", "2023-01-01", "2023-12-31");
            List<Expense> expensesToys = expenseDAO.getExpenses("tubzToys", "2023-01-01", "2023-12-31");
            
            request.setAttribute("lastCollectionTotalTofee", lastCollectionTotalTofee);
            request.setAttribute("totalLast30DaysTofee", totalLast30DaysTofee);
            request.setAttribute("totalLast365DaysTofee", totalLast365DaysTofee);
            request.setAttribute("expensesTofee", expensesTofee);
            
            request.setAttribute("lastCollectionTotalToys", lastCollectionTotalToys);
            request.setAttribute("totalLast30DaysToys", totalLast30DaysToys);
            request.setAttribute("totalLast365DaysToys", totalLast365DaysToys);
            request.setAttribute("expensesToys", expensesToys);

            RequestDispatcher dispatcher = request.getRequestDispatcher("tubz.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String date = request.getParameter("date");
        String amount = request.getParameter("amount");
        String collectionMachine = request.getParameter("collectionMachine");
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
                collection.setMachine(collectionMachine);

                CollectionDAO collectionDAO = new CollectionDAOImpl(connection);
                collectionDAO.saveCollection(collection);
            } else if (expenseDate != null && expenseAmount != null && expenseReason != null && expenseAction != null && expenseAction.equals("addExpense")) {
                Expense expense = new Expense();
                expense.setDate(expenseDate);
                expense.setAmount(Double.parseDouble(expenseAmount));
                expense.setMachine(expenseMachine);
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

        response.sendRedirect("tubzServlet");
    }
    

}

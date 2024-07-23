package com.letslearn.Dao;

import com.letslearn.Interface.ExpenseDAO;
import com.letslearn.Modal.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO {
    private Connection connection;

    public ExpenseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveExpense(Expense expense) {
        String query = "INSERT INTO expenses (date, amount, machine, reason, expenseAction) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, expense.getDate());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getMachine());
            stmt.setString(4, expense.getReason());
            stmt.setString(5, expense.getAction());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Expense> getExpenses(String machine, String startDate, String endDate) {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses WHERE machine = ? AND date BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, machine);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setDate(rs.getString("date"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setMachine(rs.getString("machine"));
                expense.setReason(rs.getString("reason"));
                expense.setAction(rs.getString("expenseAction"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
    
    

    public List<Expense> getALLExpenses(){
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setDate(rs.getString("date"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setMachine(rs.getString("machine"));
                expense.setReason(rs.getString("reason"));
                expense.setAction(rs.getString("expenseAction"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
    
    @Override
    public void removeExpense(String date, String amount, String action, String machine) {
    	String query = "UPDATE expenses SET expenseAction = 'removeExpense' WHERE date = ? AND amount = ?  AND machine = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, date);
            stmt.setString(2, amount);
            stmt.setString(3, machine);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

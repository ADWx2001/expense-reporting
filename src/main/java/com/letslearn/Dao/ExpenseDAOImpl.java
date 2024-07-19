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
        String query = "INSERT INTO expenses (date, amount, machine) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, expense.getDate());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getMachine());
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
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}

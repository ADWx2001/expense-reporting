package com.letslearn.Interface;

import com.letslearn.Modal.Expense;

import java.util.List;

public interface ExpenseDAO {
    void saveExpense(Expense expense);
    List<Expense> getExpenses(String machine, String startDate, String endDate);
}

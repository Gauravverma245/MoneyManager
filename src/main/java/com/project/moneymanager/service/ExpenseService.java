package com.project.moneymanager.service;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long Id) throws Exception;
    void deleteExpenseById(Long id) throws Exception;
    Expense addExpense(Expense expense);
    Expense updateExpenseDetails(Long id, Expense expense) throws Exception;

    List<Expense> readByCategory(String category);
    List<Expense> readByName(String name);

    List<Expense> readByDateRange(Date startDate, Date endDate);
}

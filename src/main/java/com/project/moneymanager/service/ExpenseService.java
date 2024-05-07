package com.project.moneymanager.service;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.Expense;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long Id) throws Exception;
    void deleteExpenseById(Long id) throws Exception;
    Expense addExpense(Expense expense);
    Expense updateExpenseDetails(Long id, Expense expense) throws Exception;
}

package com.project.moneymanager.service;/*
 * @author gauravverma
 */

import com.project.moneymanager.exceptions.ResourceNotFoundException;
import com.project.moneymanager.model.Expense;
import com.project.moneymanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;
    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId());
    }

    @Override
    public Expense getExpenseById(Long id) throws Exception {
        Expense expense = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if(expense != null)
            return expense;
        else {
            throw new ResourceNotFoundException("Expense is not found for id " + id);
        }
    }

    @Override
    public void deleteExpenseById(Long id) throws Exception {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        expense.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) throws Exception {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        return expenseRepository.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category) {
        return expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category);
    }

    @Override
    public List<Expense> readByName(String name) {
        return expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), name);
    }

    @Override
    public List<Expense> readByDateRange(Date startDate, Date endDate) {
        return expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate);
    }
}

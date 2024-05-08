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
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long Id) throws Exception {
        Optional<Expense> expense = expenseRepository.findById(Id);
        return expense.orElseThrow(() -> new ResourceNotFoundException("Expense is not found for id " + Id));
    }

    @Override
    public void deleteExpenseById(Long id) throws Exception {
        if(expenseRepository.existsById(id)){
            expenseRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Expense with id: " + id + " doesn't exist");
        }
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense newExpense) throws Exception {
        Expense existingExpense = getExpenseById(id);
        if(existingExpense != null){
           return expenseRepository.save(newExpense);
        }else {
            throw new ResourceNotFoundException("No Expense exists with id: " + id);
        }
    }

    @Override
    public List<Expense> readByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    @Override
    public List<Expense> readByName(String name) {
        return expenseRepository.findByNameContaining(name);
    }

    @Override
    public List<Expense> readByDateRange(Date startDate, Date endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }
}

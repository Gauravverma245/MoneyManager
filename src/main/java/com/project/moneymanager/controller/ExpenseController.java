package com.project.moneymanager.controller;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.Expense;
import com.project.moneymanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id) throws Exception {
        return expenseService.getExpenseById(id);
    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam("id") Long id) throws Exception {
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses/add")
    public Expense addExpense(@Valid @RequestBody Expense expense){
        return expenseService.addExpense(expense);
    }

    @PutMapping("/expenses/update/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable("id") Long id) throws Exception {
        return expenseService.updateExpenseDetails(id, expense);
    }

}

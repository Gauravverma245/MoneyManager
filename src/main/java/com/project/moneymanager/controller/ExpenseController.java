package com.project.moneymanager.controller;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.Expense;
import com.project.moneymanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam("category") String category){
        return expenseService.readByCategory(category);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam("name") String name){
        return expenseService.readByName(name);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesInBetweenDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedStartDate = formatter.parse(startDate);
        Date convertedEndDate = formatter.parse(endDate);
        return expenseService.readByDateRange(convertedStartDate, convertedEndDate);
    }

}

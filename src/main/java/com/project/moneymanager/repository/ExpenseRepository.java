package com.project.moneymanager.repository;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);
    List<Expense> findByNameContaining(String name);
    List<Expense> findByDateBetween(Date startDate, Date endDate);
}

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
    List<Expense> findByUserIdAndCategory(Long userId, String category);
    List<Expense> findByUserIdAndNameContaining(Long userId, String name);
    List<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate);
    List<Expense> findByUserId(Long userId);
    Expense findByUserIdAndId(Long userId, Long expenseId);
}

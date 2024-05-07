package com.project.moneymanager.repository;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

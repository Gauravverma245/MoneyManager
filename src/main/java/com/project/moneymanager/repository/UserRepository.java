package com.project.moneymanager.repository;/*
 * @author gauravverma
 */

import com.project.moneymanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
}

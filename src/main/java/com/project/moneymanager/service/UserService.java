package com.project.moneymanager.service;/*
 * @author gauravverma
 */

import com.project.moneymanager.dto.UserDto;
import com.project.moneymanager.model.User;

public interface UserService {
    UserDto createUser(User user) throws Exception;
    UserDto readUser(Long id) throws Exception;
    UserDto updateUser(User user, Long id);
    void deleteUser(Long id) throws Exception;
}

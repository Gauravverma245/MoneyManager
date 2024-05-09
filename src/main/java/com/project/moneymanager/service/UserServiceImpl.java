package com.project.moneymanager.service;/*
 * @author gauravverma
 */

import com.project.moneymanager.dto.UserDto;
import com.project.moneymanager.exceptions.ResourceNotFoundException;
import com.project.moneymanager.model.User;
import com.project.moneymanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDto createUser(User user) throws Exception {
        user.setCreateAt(new Timestamp(System.currentTimeMillis()));
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        }catch (Exception e){
            throw new Exception("Failed to create user", e);
        }
    }

    @Override
    public UserDto readUser() throws Exception {
        Long userId = getLoggedInUser().getId();
        try {
            User savedUser = userRepository.findById(userId).get();
            return modelMapper.map(savedUser, UserDto.class);
        }catch (Exception e){
            throw new ResourceNotFoundException("user with id : " + userId + " doesn't exist.");
        }
    }

    @Override
    public UserDto updateUser(User user) {
        User existingUser = userRepository.findById(getLoggedInUser().getId()).get();
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
        return modelMapper.map(userRepository.save(existingUser), UserDto.class);
    }

    @Override
    public void deleteUser(){
        User existingUser = userRepository.findById(getLoggedInUser().getId()).get();
        userRepository.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email " + email));
    }
}

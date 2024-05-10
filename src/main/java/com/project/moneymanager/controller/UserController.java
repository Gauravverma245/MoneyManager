package com.project.moneymanager.controller;/*
 * @author gauravverma
 */

import com.project.moneymanager.dto.UserDto;
import com.project.moneymanager.model.User;
import com.project.moneymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<UserDto> getUserProfile() throws Exception {
        return new ResponseEntity<UserDto>(userService.readUser(), HttpStatus.OK);
    }

    @PutMapping("/user/update")
    public ResponseEntity<UserDto> updateUserDetails(@RequestBody User user) {
        return new ResponseEntity<UserDto>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/user/deactivate")
    public ResponseEntity<HttpStatus> deleteUserDetails() throws Exception {
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }


}

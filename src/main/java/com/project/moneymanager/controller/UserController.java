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

    @PostMapping("/user/register")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<UserDto>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/profile/{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable Long id) throws Exception {
        return new ResponseEntity<UserDto>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user, @PathVariable Long id) {
        return new ResponseEntity<UserDto>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }


}

package com.project.moneymanager.controller;/*
 * @author gauravverma
 */

import com.project.moneymanager.dto.JwtResponse;
import com.project.moneymanager.dto.LoginDto;
import com.project.moneymanager.dto.UserDto;
import com.project.moneymanager.model.User;
import com.project.moneymanager.security.CustomUserDetailsService;
import com.project.moneymanager.service.UserService;
import com.project.moneymanager.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/user/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto login) throws Exception {
        authenticate(login.getEmail(), login.getPassword());
        // generate Jwt Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (DisabledException e){
            throw new Exception("user disabled.");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credentials.");
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<UserDto>(userService.createUser(user), HttpStatus.CREATED);
    }
}

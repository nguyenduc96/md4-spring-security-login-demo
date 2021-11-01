package com.nguyenduc.controller;

import com.nguyenduc.model.login.JwtResponse;
import com.nguyenduc.model.login.Role;
import com.nguyenduc.model.login.User;
import com.nguyenduc.model.login.UserPrinciple;
import com.nguyenduc.service.login.jwt.JwtService;
import com.nguyenduc.service.login.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        JwtResponse jwtResponse = new JwtResponse(
                currentUser.getId(),
                jwt,
                userDetails.getUsername(),
                currentUser.getFirstName(),
                currentUser.getLastName(),
                userDetails.getAuthorities());
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if(user.getRoles() == null) {
            List<Role> roles = new ArrayList<>();
            roles.add(new Role(2L, null));
            user.setRoles(roles);
        }
        User userRegister = userService.save(user);
        if (userRegister == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userRegister, HttpStatus.CREATED);
    }
}

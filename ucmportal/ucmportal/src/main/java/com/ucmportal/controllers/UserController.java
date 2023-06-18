package com.ucmportal.controllers;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucmportal.dto.LoginDto;
import com.ucmportal.entities.User;
import com.ucmportal.services.LoginResponse;
import com.ucmportal.services.UserService;

@RestController
@RequestMapping("/ucmportal") // untuk login maupun register
public class UserController {

    @Autowired
    private UserService userService;

    // ========================== register user=============================================
    @PostMapping("/register") //
    public ResponseEntity<User> create(@RequestBody User user) {
        if (user.getLevel() == null || user.getLevel().isEmpty()) {
            user.setLevel("user"); // set default nya ke user
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    // =================================login================================================

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto) {
        LoginResponse loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
    //===================================profile============================================
    @GetMapping("/{username}/profile") 
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
    // ===================================test=============================================
    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() { // Hanya untuk Test
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{nim}") // Hanya untuk Test
    public void deleteByNim(@PathVariable("nim") Long nim) {
        userService.removeOne(nim);
    }


}

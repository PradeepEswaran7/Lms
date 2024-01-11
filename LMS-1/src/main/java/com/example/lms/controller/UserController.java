package com.example.lms.controller;

import java.util.List;

//UserController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.exception.UserNotFoundException;
import com.example.lms.model.User;
import com.example.lms.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping
public class UserController {

 @Autowired
 private UserService userService;
 
 @PostMapping("/register")
 public ResponseEntity<String> registerUser(@RequestBody User user) {
     String result = userService.registerUser(user);
     return ResponseEntity.status(result.contains("successful") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(result);
 }

 @PostMapping("/login")
 public ResponseEntity<String> loginUser(@RequestBody User user) {
     String result = userService.loginUser(user);
     return ResponseEntity.status(result.contains("successful") ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(result);    
 }
 @GetMapping("/students")
 public ResponseEntity<List<User>> getAllUsers() {
     try {
         List<User> users = userService.getAllUsers();
         return ResponseEntity.ok(users);
     } catch (Exception e) {
         // Log the exception using a logging framework
         // logger.error("Error while fetching all users", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }
 }

 @GetMapping("/{id}")
 public ResponseEntity<User> getUserById(@PathVariable int id) {
     try {
         User user = userService.getUserById(id);
         return ResponseEntity.ok(user);
     } catch (UserNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteUser(@PathVariable int id) {
     try {
         userService.getUserById(id); // Check if user exists before deleting
         userService.deleteUserById(id);
         return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
     } catch (UserNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID " + id);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting user with ID " + id);
     }
 }
 @PutMapping("/{id}")
 public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable int id) {
     try {
         User updatedUser = userService.updateUser(newUser, id);
         return ResponseEntity.ok(updatedUser);
     } catch (UserNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }
 }
 @GetMapping("/getStdId/{email}")
 public ResponseEntity<Integer> getStdIdByEmail(@PathVariable String email) {
     try {
         int stdId = userService.getStdIdByEmail(email);
         return ResponseEntity.ok(stdId);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
     }
 }

}

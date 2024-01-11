package com.example.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.model.Faculty;
import com.example.lms.model.User;
import com.example.lms.service.FacultyService;
import com.example.lms.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping

public class FacultyController {
	@Autowired
	 private FacultyService facultyService;
	
	@PostMapping("/facultlogin")
	 public ResponseEntity<String> loginUser(@RequestBody Faculty faculty) {
	     String result = facultyService.loginUser(faculty);
	     return ResponseEntity.status(result.contains("successful") ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(result);    
	 }
	@GetMapping("/faculty")
	 public ResponseEntity<List<Faculty>> getAllFaculty() {
	     try {
	         List<Faculty> faculty = facultyService.getAllFaculty();
	         return ResponseEntity.ok(faculty);
	     } catch (Exception e) {
	         // Log the exception using a logging framework
	         // logger.error("Error while fetching all users", e);
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	     }
	 }
}

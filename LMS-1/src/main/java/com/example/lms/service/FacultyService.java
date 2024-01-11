package com.example.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dao.FacultyRepository;
import com.example.lms.dao.UserRepository;
import com.example.lms.model.Faculty;
import com.example.lms.model.User;

@Service
public class FacultyService {
	@Autowired
    private FacultyRepository facultyRepository;
	
	public String loginUser(Faculty faculty) {
        try {
            User existingUser = facultyRepository.findByEmail_Address(faculty.getFaculty_address());

            if (existingUser != null && existingUser.getPassword().equals(faculty.getPassword())) {
                // Login successful
                return "Login successful!";
            } else {
                // User not found or incorrect password
                return "Invalid credentials!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Login unsuccessful!";
        }
    }
	
	 public List<Faculty> getAllFaculty() {
	    	return facultyRepository.findAll();
	    }
}

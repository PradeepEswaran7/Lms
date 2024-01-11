package com.example.lms.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dao.UserRepository;
import com.example.lms.exception.UserNotFoundException;
import com.example.lms.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        try {
            user.setRole("student");
            userRepository.save(user);
            return "Registration successful!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Registration unsuccessful!";
        }
    }

    public String loginUser(User user) {
        try {
            User existingUser = userRepository.findByEmail_Address(user.getEmail_Address());

            if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
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
    
    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }
    
    
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException((int) id));
    }
    
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
    
    public User updateUser(User user, int id) {
    	user.setStd_id(id);
		return this.userRepository.save(user);
	}
    
    public int getStdIdByEmail(String email) {
        User user = userRepository.findByEmail_Address(email);
        return user != null ? user.getStd_id() : -1; // Return -1 or some default value if user not found
    }
}
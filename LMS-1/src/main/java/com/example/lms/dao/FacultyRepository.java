package com.example.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lms.model.Faculty;
import com.example.lms.model.User;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    // Additional custom queries or methods can be added here if needed
	@Query("SELECT u FROM User u WHERE u.email_Address = ?1")
	User findByEmail_Address(String email_Address);
}

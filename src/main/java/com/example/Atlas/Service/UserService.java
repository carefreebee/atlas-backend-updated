package com.example.Atlas.Service;


import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.UserEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.UserRepository;
import com.example.Atlas.exception.*;



@Service
public class UserService {
    @Autowired
    UserRepository userrepo;

    @Autowired
    DepartmentRepository departmentrepo;
    
     @Autowired
    private PasswordEncoder passwordEncoder;

   
    
    public UserEntity insertUser(UserEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        request.setDepartment(department);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userrepo.save(request);
    }

    // Verify the password during login
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean checkEmailExists(String email) {
        return userrepo.existsByEmail(email);
    }

    public boolean checkUserNameExists(String username) {
        return userrepo.existsByUsername(username);
    }
    
    public UserEntity getUserByUsername(String username) {
        return userrepo.findByUsername(username);
    }

    // Get user by ID
    public UserEntity getUserById(int id) {
        Optional<UserEntity> UserOptional = userrepo.findById(id);
        return UserOptional.orElse(null);
    }
    

    public boolean updateUserDetails(
            int id,
            String firstname,
            String lastname,
            String role,
            String email,
            int age,
            Date birthdate

    ) {
        try {
            // Get the user entity by username
            Optional<UserEntity> userOptional = userrepo.findById(id);
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                // Update the user details
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setRole(role);
                user.setEmail(email);
                user.setAge(age);
                user.setBirthdate(birthdate);
                userrepo.save(user);
                return true; // User details successfully updated
            } else {
                return false; // No user found with the given username
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating user details.", e);
        }
    }


    public void updatePassword(int userId, String currentPassword, String newPassword) {
        UserEntity user = userrepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Check if the current password matches
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidPasswordException("Current password is incorrect");
        }

        // Encode the new password
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userrepo.save(user);
    }
    
    public int getUserCount() {
        return (int) userrepo.count();
    }

    public List<UserEntity> getAllUsers() {
        return userrepo.findAll();
    }

    public List<Map<String, Object>> getUserCountByRole() {
        List<UserEntity> users = userrepo.findAll();
        Map<String, Integer> roleCounts = new HashMap<>();
        
        for (UserEntity user : users) {
            String role = user.getRole();
            roleCounts.put(role, roleCounts.getOrDefault(role, 0) + 1);
        }
        
        List<Map<String, Object>> roleCountList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : roleCounts.entrySet()) {
            Map<String, Object> roleCount = new HashMap<>();
            roleCount.put("roleName", entry.getKey());
            roleCount.put("userCount", entry.getValue());
            roleCountList.add(roleCount);
        }
        
        return roleCountList;
    }   
 
}

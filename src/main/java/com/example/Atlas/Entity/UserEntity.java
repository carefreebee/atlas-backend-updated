package com.example.Atlas.Entity;

import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String firstname;

    private String lastname;

    private String role;

    private String username;

    private String email;

    private String password;

    private String head;
    
    @Column(columnDefinition = "int DEFAULT '0'")
    private int age;

    private Date birthdate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // Specify the foreign key column
    private DepartmentEntity department;

    @Column(columnDefinition = "tinyint DEFAULT '0'")
    private int hasPrimaryStrats;

    // Getters and setters for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     // Getters and setters for firstname
     public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

      // Getters and setters for lastname
      public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    

      // Getters and setters for lastname
      public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getters and setters for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters and setters for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and setters for password 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Getters and setters for age 
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getters and setters for birthdate 
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    // Getters and setters for department
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public int getHasPrimaryStrats() {
        return hasPrimaryStrats;
    }

    public void setHasPrimaryStrats(int hasPrimaryStrats) {
        this.hasPrimaryStrats = hasPrimaryStrats;
    }

    // Getters and setters for birthdate 
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}

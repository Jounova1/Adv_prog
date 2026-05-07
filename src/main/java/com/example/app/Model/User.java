package com.example.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Integer age;
    private String qualification;
    private String experience;
    private boolean isActive;
    public User() {}
    public User(Long id, String firstName, String lastName, String email, String phone, boolean isActive) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.isActive = isActive;
}


    
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean getIsActive() { return isActive; }

    public String getPassword() { return password; }
    public Integer getAge() { return age; }
    public String getQualification() { return qualification; }
    public String getExperience() { return experience; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public void setPassword(String password) { this.password = password; }
    public void setAge(Integer age) { this.age = age; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public void setExperience(String experience) { this.experience = experience; }
}
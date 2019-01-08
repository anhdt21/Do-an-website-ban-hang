package com.cnpm.doanwebbanhang.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "không được để trống")
    @Size(min = 4, max = 30, message = "tên từ 4 đến 30 ký tự")
    private String userName;
    @NotEmpty(message = "không được để trống")
    private String password;
    @NotEmpty(message = "không được để trống")
    private String name;
    @Email
    @NotBlank(message = "email không được để trống")
    private String email;
    @NotEmpty(message = "không được để trống")
    private String address;
    @NotBlank(message = "không được để trống")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private String birthDay;
    @Pattern(regexp = "^0[0-9]{9}$", message = "số điện thoại không đúng định dạng")
    private String phone;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    public UserRole userRole;

    public User() {

    }

    public User(@NotEmpty(message = "không được để trống") @Size(min = 4, max = 30, message = "tên từ 4 đến 30 ký tự") String userName, @NotEmpty(message = "không được để trống") String password, @NotEmpty(message = "không được để trống") String name, @Email @NotBlank(message = "email không được để trống") String email, @NotEmpty(message = "không được để trống") String address, @NotBlank(message = "không được để trống") String birthDay, @Pattern(regexp = "^0[0-9]{9}$", message = "số điện thoại không đúng định dạng") String phone, UserRole userRole) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthDay = birthDay;
        this.phone = phone;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
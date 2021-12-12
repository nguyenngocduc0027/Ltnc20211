package com.example.ltnc20211.quanly;

public class QuanLyHelperClass {

    String email, name, age, password;

    public QuanLyHelperClass() {
    }

    public QuanLyHelperClass(String email, String name, String age, String password) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

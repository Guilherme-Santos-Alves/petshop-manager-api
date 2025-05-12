package com.guilhermesantos.petshop.dto;

public class UpdateOwnerDTO {
    private String phone;
    private String email;
    private String address;
    private String password;

    public UpdateOwnerDTO(String phone, String email, String password, String address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

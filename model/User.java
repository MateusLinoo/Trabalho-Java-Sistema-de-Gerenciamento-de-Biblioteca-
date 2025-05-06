package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private List<Loan> activeLoans;

    public User(int id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.activeLoans = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Loan> getActiveLoans() {
        return activeLoans;
    }
    public void addLoan(Loan loan) {
        this.activeLoans.add(loan);
    }
    public void removeLoan(Loan loan) {
        this.activeLoans.remove(loan);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", phone='" + phone + '\'' +
               ", address='" + address + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
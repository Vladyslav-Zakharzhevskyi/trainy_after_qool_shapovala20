package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @Column(name = "user_name", columnDefinition = "varchar(20)", nullable = false)
    private String userName;

    @Column(name = "first_name", columnDefinition = "varchar(20)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(20)", nullable = false)
    private String lastName;

    @Column(name = "email", columnDefinition = "varchar(40)", nullable = false)
    private String email;

    @Column(name = "email_confirmed", columnDefinition = "boolean")
    private Boolean emailConfirmed = Boolean.TRUE;

    @Column(name = "password", columnDefinition = "varchar(60)", nullable = false)
    private String password;

    @Column(name = "age", columnDefinition = "integer")
    private Integer age;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "salary", columnDefinition = "double precision")
    private Double salary;

    @Column(name = "currency", columnDefinition = "varchar(3)")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "address")
    private String address;

    public Person() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

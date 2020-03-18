package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(name = "position", columnDefinition = "varchar(100)", nullable = false)
    private String position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @ManyToMany(mappedBy = "jobs")
    private List<Person> employees = new ArrayList<>();

    @Column(name = "strategy", columnDefinition = "varchar(255)")
    private String strategy;

    public Job() {}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }

    public void addEmployee(Person person) {
        if (employees.contains(person)) {
            return;
        }
//        person.addJob(this);
        employees.add(person);
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}

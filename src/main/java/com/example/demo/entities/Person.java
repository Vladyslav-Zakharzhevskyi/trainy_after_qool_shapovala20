package com.example.demo.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "varchar(20)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(20)", nullable = false)
    private String lastName;

    @Column(name = "age", columnDefinition = "integer", nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "persons_has_jobs",
                    joinColumns = {@JoinColumn(name = "person_id")},
                    inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<Job> jobs = new ArrayList<>();

    @Column(name = "salary", columnDefinition = "double precision", nullable = false)
    private Double salary;

    @Column(name = "currency", columnDefinition = "varchar(3)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Person() {}

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        if (addresses.contains(address)) {
            return;
        }
        addresses.add(address);
        address.setPerson(this);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job) {
        if (jobs.contains(job)) {
            return;
        }
        jobs.add(job);
        job.addEmployee(this);
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean containAddress(Address address) {
        if (address == null || address.getId() == null) {
            return false;
        }
        return addresses.contains(address);
    }
}

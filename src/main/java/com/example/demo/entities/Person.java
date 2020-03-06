package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Integer age;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany(mappedBy = "employees")
    private List<Job> jobs;

    @Column
    private Double salary;

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
        if (addresses.contains(address))
            return;

        address.setPerson(this);
        addresses.add(address);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(age, person.age) &&
                Objects.equals(addresses, person.addresses) &&
                Objects.equals(jobs, person.jobs) &&
                Objects.equals(salary, person.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, addresses, jobs, salary);
    }
}

package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Column(name = "city", columnDefinition = "varchar(100)", nullable = false)
    private String city;

    @Column(name = "street", columnDefinition = "varchar(100)", nullable = false)
    private String street;

    @Column(name = "building_num", columnDefinition = "varchar(10)", nullable = false)
    private String buildingNum;

    @Column(name = "address_type", columnDefinition = "varchar(15)", nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    public Address() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        person.addAddress(this);
    }

}

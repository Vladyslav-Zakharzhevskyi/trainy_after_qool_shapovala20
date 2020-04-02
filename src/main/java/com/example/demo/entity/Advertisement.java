package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "advertisement")
public class Advertisement extends BaseEntity {

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "type", nullable = false, length = 8)
    private String type;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "room_count", nullable = false)
    private Integer roomCount;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Column(name = "is_bargain", nullable = false)
    private Boolean isBargain;

    @Column(name = "creation_date")
    private Date dateCreation;

    @Column(name = "update_date")
    private Date dateUpdated;

    @Column(name = "client_name", nullable = false, length = 15)
    private String clientName;

    @Column(name = "client_email", nullable = false, length = 15)
    private String clientEmail;

    @Column(name = "client_phone", nullable = false, length = 20)
    private String clientPhone;

    @Column(name = "client_city", nullable = false, length = 15)
    private String clientCity;

    public Advertisement() {}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Boolean getBargain() {
        return isBargain;
    }

    public void setBargain(Boolean bargain) {
        isBargain = bargain;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }
}

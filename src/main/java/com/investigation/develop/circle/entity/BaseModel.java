package com.investigation.develop.circle.entity;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    public BaseModel() {
    }

    public BaseModel(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}

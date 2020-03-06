package com.example.demo.entities.mappedsuperclass;

import com.example.demo.entities.BaseEntity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseActivity extends BaseEntity {

    @Column(name = "duration")
    private Double duration;

    public BaseActivity() {}

    public BaseActivity(Double duration) {
        this.duration = duration;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}

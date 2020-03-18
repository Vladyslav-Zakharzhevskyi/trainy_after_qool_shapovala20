package com.example.demo.entity.mappedsuperclass;

import com.example.demo.entity.BaseEntity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseActivity extends BaseEntity {

    @Column(name = "duration", columnDefinition = "double precision", nullable = false)
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

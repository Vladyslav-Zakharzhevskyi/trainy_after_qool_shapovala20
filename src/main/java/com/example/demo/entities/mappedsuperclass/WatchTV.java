package com.example.demo.entities.mappedsuperclass;

import javax.persistence.*;

@Entity
@Table(name = "activity_watching_tv")
public class WatchTV extends BaseActivity {

    @Column
    private String name;

    @Column
    private Integer appraisalValue = 0;

    public WatchTV() {}

    public WatchTV(String name, Double duration) {
        super(duration);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAppraisalValue() {
        return appraisalValue;
    }

    public void setAppraisalValue(Integer appraisalValue) {
        this.appraisalValue = appraisalValue;
    }
}

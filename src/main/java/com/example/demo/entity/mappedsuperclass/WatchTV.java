package com.example.demo.entity.mappedsuperclass;

import javax.persistence.*;

@Entity
@Table(name = "activity_watching_tv")
public class WatchTV extends BaseActivity {

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @Column(name = "appraisal_value", columnDefinition = "integer")
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

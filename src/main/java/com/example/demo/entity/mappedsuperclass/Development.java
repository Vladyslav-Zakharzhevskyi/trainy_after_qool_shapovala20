package com.example.demo.entity.mappedsuperclass;

import javax.persistence.*;

@Entity
@Table(name = "activity_development")
public class Development extends BaseActivity {

    @Column(name = "language", columnDefinition = "varchar(100)", nullable = false)
    private String language;

    @Column(name = "used_device", columnDefinition = "varchar(100)", nullable = false)
    private String usedDevice;

    public Development() {
    }

    public Development(String language, String usedDevice, Double duration) {
        super(duration);
        this.language = language;
        this.usedDevice = usedDevice;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsedDevice() {
        return usedDevice;
    }

    public void setUsedDevice(String usedDevice) {
        this.usedDevice = usedDevice;
    }
}

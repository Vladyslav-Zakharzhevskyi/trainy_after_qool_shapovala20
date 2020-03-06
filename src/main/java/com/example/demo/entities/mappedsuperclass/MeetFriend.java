package com.example.demo.entities.mappedsuperclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activity_meet_friend")
public class MeetFriend extends BaseActivity {

    @Column(name = "person_name")
    private String friendName;

    @Column
    private String address;

    public MeetFriend() {}

    public MeetFriend(String friendName, String address, Double duration) {
        super(duration);
        this.friendName = friendName;
        this.address = address;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getAddress() {
        return address;
    }
}

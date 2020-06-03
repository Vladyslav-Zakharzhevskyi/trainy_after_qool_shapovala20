package com.investigation.develop.circle.entity;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseSimpleModel {

    @Column(name = "role_name")
    private String roleName;

    public Role() {}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "ROLE=" + roleName;
    }
}

package com.investigation.develop.circle.repository;

import com.investigation.develop.circle.entity.SystemProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPropertyRepository extends JpaRepository<SystemProperty, Integer> {

    SystemProperty findSystemPropertyByKey(String key);

}

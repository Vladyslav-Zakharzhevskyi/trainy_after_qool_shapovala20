package com.example.demo.repository;

import com.example.demo.entity.SystemProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPropertyRepository extends JpaRepository<SystemProperty, Integer> {

    SystemProperty findSystemPropertyByKey(String key);

}

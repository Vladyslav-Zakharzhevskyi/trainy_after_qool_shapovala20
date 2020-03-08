package com.example.demo.repository;

import com.example.demo.entities.mappedsuperclass.BaseActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseActionRepository<T extends BaseActivity> extends JpaRepository<T, UUID> {
}

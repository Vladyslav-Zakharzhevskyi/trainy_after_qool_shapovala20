package com.example.demo.repository;

import com.example.demo.entities.mappedsuperclass.BaseActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseActionRepository<T extends BaseActivity> extends CrudRepository<T, UUID> {
}

package com.example.demo.repository;

import com.example.demo.entities.EntityA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntityARepository extends CrudRepository<EntityA, UUID> {


}

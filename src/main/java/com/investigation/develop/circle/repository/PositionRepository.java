package com.investigation.develop.circle.repository;

import com.investigation.develop.circle.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}

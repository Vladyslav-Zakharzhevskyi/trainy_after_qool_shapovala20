package com.investigation.develop.circle.repository;

import com.investigation.develop.circle.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> getAllByArchivedFalse();
}

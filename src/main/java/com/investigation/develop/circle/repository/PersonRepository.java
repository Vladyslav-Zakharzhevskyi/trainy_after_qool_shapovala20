package com.investigation.develop.circle.repository;

import com.investigation.develop.circle.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findPersonByUserName(String username);

    @Query("SELECT DISTINCT person FROM Person person INNER JOIN Offer o ON person=o.author WHERE o.archived=false")
    List<Person> findPersonsWithOffers();

}

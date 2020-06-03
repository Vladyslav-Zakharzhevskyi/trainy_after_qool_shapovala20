package com.investigation.develop.circle.service;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.investigation.develop.circle.entity.Offer;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.entity.Role;
import com.investigation.develop.circle.repository.OfferRepository;
import com.investigation.develop.circle.system.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private OfferRepository offerRepository;

    public boolean checkEnableToEditOffer(Long offerId, Principal principal) {
        Optional<Offer> proposal = offerRepository.findById(offerId);

        return proposal
                .map(offer -> Objects.equal(offer.getAuthor().getId(), Context.getUser(principal).getId()))
                .orElse(false);
    }

    public boolean isUserAdmin(Person person) {
        return isUserHasRole(person, "ADMIN");
    }

    public boolean isUserHasRole(Person person, String role) {
        return !person.getRoles().isEmpty() &&
                person.getRoles().stream()
                        .map(Role::getRoleName)
                        .anyMatch(Predicates.equalTo(role));
    }


}

package com.investigation.develop.circle.service;

import com.google.common.base.Objects;
import com.investigation.develop.circle.entity.Offer;
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

}

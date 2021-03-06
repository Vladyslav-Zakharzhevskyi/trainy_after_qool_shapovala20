package com.investigation.develop.circle.service;

import com.investigation.develop.circle.entity.Offer;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.exception.ApplicationException;

import java.util.List;

public interface OfferService {

    List<Offer> getActiveOffers();

    Offer addOffer(String message, boolean isAnonymous, Person author);

    Offer updateOffer(Long offerId, String message, boolean isAnonymous) throws ApplicationException;

    void deleteOffer(Long offerId) throws ApplicationException;

}

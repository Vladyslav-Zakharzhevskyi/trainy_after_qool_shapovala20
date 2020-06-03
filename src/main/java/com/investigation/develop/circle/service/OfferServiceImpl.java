package com.investigation.develop.circle.service;

import com.investigation.develop.circle.entity.Offer;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.exception.ApplicationException;
import com.investigation.develop.circle.exception.Code;
import com.investigation.develop.circle.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> getActiveOffers(){
        return offerRepository.getAllByArchivedFalse();
    }

    @Override
    public Offer addOffer(String message, boolean addAsAnonymous, Person user) {
        Offer offer = new Offer();
        offer.setMessage(message);
        offer.setAuthor(user);
        offer.setPostedAnonymously(addAsAnonymous);
        offer.setCreatedAt(Date.from(Instant.now()));

        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Long offerId, String message, boolean isAnonymous) throws ApplicationException {
        Optional<Offer> offer = offerRepository.findById(offerId);

        Offer updatedOffer = offer.map(origin -> {
            origin.setMessage(message);
            origin.setUpdatedAt(Date.from(Instant.now()));
            origin.setPostedAnonymously(isAnonymous);
            return origin;
        }).orElseThrow(() -> new ApplicationException(String.format("Can't update message, due it has not found, id: %S", offerId), Code.GENERAL_EXCEPTION));

        return offerRepository.save(updatedOffer);
    }

    @Override
    public void deleteOffer(Long offerId) throws ApplicationException {
        Optional<Offer> offerToArchive = offerRepository.findById(offerId);

        Offer archived = offerToArchive.map(offer -> {
            offer.setArchived(true);
            offer.setUpdatedAt(Date.from(Instant.now()));
            return offer;
        }).orElseThrow(() -> new ApplicationException(String.format("Offer with id: %s doesn't found", offerId), Code.VALUE_MISS_MATCH));

        offerRepository.save(archived);
    }



}

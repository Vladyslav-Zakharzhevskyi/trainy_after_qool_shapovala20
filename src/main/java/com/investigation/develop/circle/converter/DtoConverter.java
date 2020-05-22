package com.investigation.develop.circle.converter;

import com.investigation.develop.circle.dto.OfferDto;
import com.investigation.develop.circle.entity.Offer;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {

    public static List<OfferDto> convert(List<Offer> offers) {
        return offers.stream().map(DtoConverter::convert).collect(Collectors.toList());
    }

    public static OfferDto convert(Offer offer) {
        return new OfferDto(offer.getId(),
                offer.getMessage(),
                offer.getCreatedAt(),
                offer.getUpdatedAt(),
                offer.isPostedAnonymously() ? "Anonymous" : offer.getAuthor().getFirstName(),
                offer.getAuthor().getId(),
                offer.isPostedAnonymously());
    }

}

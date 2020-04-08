package com.example.demo.controller;

import com.example.demo.converter.AdvertisementDtoConverter;
import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.HomeBusinessUser;
import com.example.demo.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementDtoConverter advertisementDtoConverter;

    @RequestMapping(value = "/advertisement", method = RequestMethod.POST)
    public AdvertisementDto saveAdvertisement(@AuthenticationPrincipal HomeBusinessUser user, @RequestBody AdvertisementDto advertisement) {

        Advertisement savedAdv = advertisementRepository.save(advertisementDtoConverter.convert(advertisement));

        return advertisementDtoConverter.convertToDto(savedAdv);
    }

    @RequestMapping(value = "/advertisement", method = RequestMethod.GET)
    public List<AdvertisementDto> getAdvertisements(@AuthenticationPrincipal HomeBusinessUser user) {

        List<Advertisement> all = advertisementRepository.findAll();

        return advertisementDtoConverter.convertToDto(all);
    }

    @RequestMapping(value = "/advertisement", method = RequestMethod.PUT)
    public AdvertisementDto updateAdvertisement(@AuthenticationPrincipal HomeBusinessUser user, @RequestBody UUID id) {
        return null;
    }

    @RequestMapping(value = "/advertisement", method = RequestMethod.DELETE)
    public AdvertisementDto deleteAdvertisement(@AuthenticationPrincipal HomeBusinessUser user, @RequestBody UUID id) {
        return null;
    }


}

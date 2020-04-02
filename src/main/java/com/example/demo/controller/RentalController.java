package com.example.demo.controller;

import com.example.demo.converter.AdvertisementDtoConverter;
import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementDtoConverter advertisementDtoConverter;

    @RequestMapping(value = "/rental/addAdvertisement", method = RequestMethod.POST)
    public AdvertisementDto saveAdvertisement(@AuthenticationPrincipal User user, @RequestBody AdvertisementDto advertisement) {

        Advertisement savedAdv = advertisementRepository.save(advertisementDtoConverter.convert(advertisement));

        return advertisementDtoConverter.convertToDto(savedAdv);
    }


}

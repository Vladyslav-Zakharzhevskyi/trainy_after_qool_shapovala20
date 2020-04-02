package com.example.demo.converter;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;

import java.util.List;

public interface AdvertisementDtoConverter {

    Advertisement convert(AdvertisementDto dto);

    List<Advertisement> convert(List<AdvertisementDto> dtos);

    AdvertisementDto convertToDto(Advertisement advertisement);

    List<AdvertisementDto> convertToDto(List<Advertisement> advertisement);

}

package com.example.demo.converter;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.ClientInfoDto;
import com.example.demo.entity.Advertisement;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementDtoConverterImpl implements AdvertisementDtoConverter {

    @Override
    public List<Advertisement> convert(List<AdvertisementDto> dtos) {
        return dtos.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public Advertisement convert(AdvertisementDto dto) {
        if (dto == null)
            return null;

        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(dto.getTitle());
        advertisement.setType(dto.getType());
        advertisement.setBargain(dto.getOpenedForBargain());
        advertisement.setCost(dto.getCost());
        advertisement.setDescription(dto.getDescription());
        advertisement.setFloor(dto.getNumberFloor());
        advertisement.setRoomCount(dto.getRoomCount());
        advertisement.setDateCreation(new Date());

        ClientInfoDto clientInfoDto = dto.getClientInfoDto();
        if (clientInfoDto != null) {
            advertisement.setClientName(clientInfoDto.getName());
            advertisement.setClientCity(clientInfoDto.getCity());
            advertisement.setClientEmail(clientInfoDto.getEmail());
            advertisement.setClientPhone(clientInfoDto.getPhoneNumber());
        }

        return advertisement;
    }


    @Override
    public List<AdvertisementDto> convertToDto(List<Advertisement> advertisement) {
        return advertisement.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @Override
    public AdvertisementDto convertToDto(Advertisement advertisement) {
        AdvertisementDto dto = new AdvertisementDto(
                advertisement.getType(),
                advertisement.getTitle(),
                new ClientInfoDto(advertisement.getClientName(),
                        advertisement.getClientEmail(),
                        advertisement.getClientPhone(),
                        advertisement.getClientCity()),
                advertisement.getDescription(),
                advertisement.getCost(),
                advertisement.getRoomCount(),
                advertisement.getRoomCount(),
                advertisement.getBargain(),
                advertisement.getDateCreation(),
                advertisement.getDateUpdated()
                );
        return dto;
    }

}

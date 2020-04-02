package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvertisementDto {

    private String type;
    private String title;
    private ClientInfoDto clientInfoDto;
    private String description;
    private Integer cost;
    private Integer roomCount;
    private Integer numberFloor;
    private boolean isOpenedForBargain;

    @JsonCreator
    public AdvertisementDto(
            @JsonProperty(value = "type") String type,
            @JsonProperty(value = "title") String title,
            @JsonProperty(value = "clientInfo") ClientInfoDto clientInfoDto,
            @JsonProperty(value = "description") String description,
            @JsonProperty(value = "cost") Integer cost,
            @JsonProperty(value = "countRoom") Integer roomCount,
            @JsonProperty(value = "floorNumber") Integer numberFloor,
            @JsonProperty(value = "isBargain") boolean isOpenedForBargain) {
        this.type = type;
        this.title = title;
        this.clientInfoDto = clientInfoDto;
        this.description = description;
        this.cost = cost;
        this.roomCount = roomCount;
        this.numberFloor = numberFloor;
        this.isOpenedForBargain = isOpenedForBargain;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public ClientInfoDto getClientInfoDto() {
        return clientInfoDto;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public Integer getNumberFloor() {
        return numberFloor;
    }

    public boolean getOpenedForBargain() {
        return isOpenedForBargain;
    }
}

package com.logistics.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String vendorId;
    private String customerName;
    private String deliveryAddress;
    private String deliveryZone;

}
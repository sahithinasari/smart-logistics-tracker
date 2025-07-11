package com.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotBlank
    private String vendorId;
    @NotBlank
    private String customerName;
    @NotBlank
    private String deliveryAddress;
    private String deliveryZone;

}
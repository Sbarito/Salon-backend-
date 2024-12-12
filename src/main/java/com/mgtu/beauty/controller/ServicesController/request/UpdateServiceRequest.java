package com.mgtu.beauty.controller.ServicesController.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateServiceRequest {
    @NonNull
    private Integer serviceId;

    @NonNull
    private String serviceName;

    @NonNull
    private Double price;
}

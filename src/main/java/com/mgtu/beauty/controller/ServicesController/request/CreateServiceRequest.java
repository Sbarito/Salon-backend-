package com.mgtu.beauty.controller.ServicesController.request;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.NonNull;

@Data
public class CreateServiceRequest {
    @NonNull
    private String serviceName;

    @NonNull
    private Double price;
}

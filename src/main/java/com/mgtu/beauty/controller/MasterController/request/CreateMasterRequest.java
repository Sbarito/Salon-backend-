package com.mgtu.beauty.controller.MasterController.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateMasterRequest {
    @NonNull
    private String masterFio;
}

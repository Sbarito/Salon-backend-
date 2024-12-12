package com.mgtu.beauty.controller.MasterController.request;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class AssignServiceRequest {
    @NonNull
    private List<Integer> mastersId;
    @NonNull
    private Integer serviceId;
}

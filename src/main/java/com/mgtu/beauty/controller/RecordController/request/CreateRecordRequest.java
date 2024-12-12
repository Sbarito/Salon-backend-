package com.mgtu.beauty.controller.RecordController.request;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateRecordRequest {
    @NonNull
    private Integer masterId;
    @NonNull
    private Integer timeSlotId;
    @NonNull
    private Integer serviceId;
    @NonNull
    private LocalDate date;
}

package com.mgtu.beauty.controller.RecordController.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class GetRecordResponse {
    private String phone;
    private Integer recordId;
    private String master_fio;
    private String service_name;
    private LocalTime time_start;
    private LocalTime time_end;
    private LocalDate date;
}

package com.mgtu.beauty.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
public class TimeSlot {
    @Id
    private Integer id;

    @NotNull
    private LocalTime timeStart;

    @NotNull
    private LocalTime timeEnd;

}
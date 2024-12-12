package com.mgtu.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
public class Record {
    @Id
    private Integer id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer timeSlotId;

    @NotNull
    private Integer masterId;

    @NotNull
    private Integer serviceId;

    @NotNull
    private Integer userId;
}
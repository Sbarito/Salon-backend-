package com.mgtu.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class MasterAndService {
    @Id
    private Integer id;

    @NotNull
    private Integer masterId;

    @NotNull
    private Integer serviceId;

}
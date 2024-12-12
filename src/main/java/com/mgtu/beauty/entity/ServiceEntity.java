package com.mgtu.beauty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class ServiceEntity {
    @Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

}
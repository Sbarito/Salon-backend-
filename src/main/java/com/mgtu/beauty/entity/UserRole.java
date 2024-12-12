package com.mgtu.beauty.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
public class UserRole implements GrantedAuthority {
    @Id
    private Integer id;

    @NotNull
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
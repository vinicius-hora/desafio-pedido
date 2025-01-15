package com.btg_desafio.pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractDateEntity {
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @PrePersist
    public void prePersist() {

        this.createdAt = new Date();

    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();

    }
}

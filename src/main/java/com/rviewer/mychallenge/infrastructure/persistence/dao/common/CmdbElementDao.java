package com.rviewer.mychallenge.infrastructure.persistence.dao.common;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder(toBuilder = true)
@MappedSuperclass
public abstract class CmdbElementDao<I extends Serializable> {

    @Id
    @Column(name = "ID")
    private I id;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = true;

}

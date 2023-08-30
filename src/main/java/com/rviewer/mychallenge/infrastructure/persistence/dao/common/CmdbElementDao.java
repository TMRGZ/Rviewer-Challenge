package com.rviewer.mychallenge.infrastructure.persistence.dao.common;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class CmdbElementDao<I extends Serializable> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;

    @Builder.Default
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = true;

}

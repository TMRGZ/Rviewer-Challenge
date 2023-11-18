package com.rviewer.mychallenge.infrastructure.persistence.projection.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class CmdbElementProjection<I extends Serializable> {

    private I id;
}

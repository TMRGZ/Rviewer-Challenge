package com.rviewer.mychallenge.infrastructure.persistence.projection.hospital;

import com.rviewer.mychallenge.infrastructure.persistence.projection.common.CmdbElementProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class HospitalProjection extends CmdbElementProjection<Long> {
}

package com.rviewer.mychallenge.infrastructure.persistence.projection.hospital;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class BasicHospitalProjection extends HospitalProjection {

    private Long id;

    private String address;

    private String name;
}

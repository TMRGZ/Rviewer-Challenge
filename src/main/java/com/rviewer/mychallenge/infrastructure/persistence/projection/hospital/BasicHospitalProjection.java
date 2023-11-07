package com.rviewer.mychallenge.infrastructure.persistence.projection.hospital;

public record BasicHospitalProjection(
        Long id,

        String address,

        String name
) implements HospitalProjection {
}

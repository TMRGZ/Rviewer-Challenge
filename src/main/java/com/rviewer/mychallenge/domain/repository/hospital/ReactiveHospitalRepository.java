package com.rviewer.mychallenge.domain.repository.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementCrudRepository;

public interface ReactiveHospitalRepository extends ReactiveElementCrudRepository<Hospital, Long> {
}

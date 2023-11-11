package com.rviewer.mychallenge.domain.repository.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementProjectableCrudRepository;

public interface ReactiveHospitalRepository extends ReactiveElementProjectableCrudRepository<Hospital, Long> {
}

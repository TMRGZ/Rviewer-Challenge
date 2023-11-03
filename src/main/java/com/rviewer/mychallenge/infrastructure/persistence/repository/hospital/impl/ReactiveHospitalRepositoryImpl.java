package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.impl;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.hospital.ReactiveHospitalRepository;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.reactive.ReactiveElementCrudRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class ReactiveHospitalRepositoryImpl extends ReactiveElementCrudRepositoryImpl<Hospital, Long> implements ReactiveHospitalRepository {
    public ReactiveHospitalRepositoryImpl(ImperativeHospitalRepositoryImpl repository) {
        super(repository);
    }
}

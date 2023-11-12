package com.rviewer.mychallenge.domain.service.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementProjectableCrudRepository;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;

public class HospitalService extends CmdbCrudService<Hospital, Long> {
    public HospitalService(ReactiveElementProjectableCrudRepository<Hospital, Long> repository) {
        super(repository);
    }
}

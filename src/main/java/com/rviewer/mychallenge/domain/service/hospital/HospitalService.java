package com.rviewer.mychallenge.domain.service.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementCrudRepository;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;

public class HospitalService extends CmdbCrudService<Hospital, Long> {
    public HospitalService(ReactiveElementCrudRepository<Hospital, Long> repository) {
        super(repository);
    }
}

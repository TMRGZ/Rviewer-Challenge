package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.impl;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.hospital.ImperativeHospitalRepository;
import com.rviewer.mychallenge.infrastructure.mapper.hospital.HospitalDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.imperative.ImperativeElementCrudRepositoryImpl;
import com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa.JpaHospitalRepository;
import org.springframework.stereotype.Component;

@Component("HOSPITAL")
public class ImperativeHospitalRepositoryImpl extends ImperativeElementCrudRepositoryImpl<Hospital, HospitalDao, Long> implements ImperativeHospitalRepository {
    public ImperativeHospitalRepositoryImpl(HospitalDaoMapper mapper, JpaHospitalRepository repository) {
        super(mapper, repository);
    }
}

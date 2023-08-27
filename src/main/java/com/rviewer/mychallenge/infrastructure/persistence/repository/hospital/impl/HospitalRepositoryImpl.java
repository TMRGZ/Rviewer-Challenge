package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.impl;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.hospital.HospitalRepository;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.ElementCrudRepositoryImpl;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import org.springframework.stereotype.Component;

@Component
public class HospitalRepositoryImpl extends ElementCrudRepositoryImpl<Hospital, HospitalDao, Long> implements HospitalRepository {
    public HospitalRepositoryImpl(GenericDaoMapper<Hospital, HospitalDao, Long> mapper, JpaElementCrudRepository<HospitalDao, Long> repository) {
        super(mapper, repository);
    }
}

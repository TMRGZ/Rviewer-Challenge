package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.impl;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;
import com.rviewer.mychallenge.domain.repository.hospital.ImperativeHospitalRepository;
import com.rviewer.mychallenge.infrastructure.mapper.hospital.HospitalDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.hospital.BasicHospitalProjection;
import com.rviewer.mychallenge.infrastructure.persistence.projection.hospital.HospitalProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.imperative.ImperativeElementProjectableCrudRepositoryImpl;
import com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa.JpaHospitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("HOSPITAL")
public class ImperativeHospitalRepositoryImpl extends ImperativeElementProjectableCrudRepositoryImpl<Hospital, HospitalProjection, HospitalDao, Long>
        implements ImperativeHospitalRepository {

    public ImperativeHospitalRepositoryImpl(
            HospitalDaoMapper mapper,
            JpaHospitalRepository repository,
            ModelMapper modelMapper
    ) {
        super(mapper, repository, modelMapper);
    }

    @Override
    public Map<CmdbRetrieveType, Class<? extends HospitalProjection>> projectionTypeMap() {
        return Map.of(
                CmdbRetrieveType.BASIC, BasicHospitalProjection.class
        );
    }
}

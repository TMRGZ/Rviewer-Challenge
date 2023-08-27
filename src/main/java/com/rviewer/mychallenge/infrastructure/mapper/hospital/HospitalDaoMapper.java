package com.rviewer.mychallenge.infrastructure.mapper.hospital;

import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring", config = GenericDtoMapper.class)
public interface HospitalDaoMapper extends GenericDaoMapper<Hospital, HospitalDao, Long> {

    default HospitalDao mapToDao(Long id) {
        return Optional.ofNullable(id)
                .map(i -> HospitalDao.builder().id(id).build())
                .orElse(null);
    }

    default Long mapToDao(HospitalDao centralHospital) {
        return Optional.ofNullable(centralHospital)
                .map(HospitalDao::getId)
                .orElse(null);
    }

}

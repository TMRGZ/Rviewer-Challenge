package com.rviewer.mychallenge.application.mapper.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HospitalDtoMapper extends GenericDtoMapper<Hospital, HospitalDto, Long> {
}

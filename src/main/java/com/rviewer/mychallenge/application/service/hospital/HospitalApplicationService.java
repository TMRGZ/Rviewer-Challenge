package com.rviewer.mychallenge.application.service.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.application.service.common.CmdbCrudApplicationService;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;
import org.springframework.stereotype.Service;

@Service
public class HospitalApplicationService extends CmdbCrudApplicationService<Hospital, HospitalDto, Long> {

    public HospitalApplicationService(GenericDtoMapper<Hospital, HospitalDto, Long> mapper, CmdbCrudService<Hospital, Long> service) {
        super(mapper, service);
    }
}

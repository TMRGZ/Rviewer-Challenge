package com.rviewer.mychallenge.infrastructure.persistence.controller.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.application.service.common.CmdbCrudApplicationService;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.infrastructure.persistence.controller.common.CmdbCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
public class HospitalController extends CmdbCrudController<Hospital, HospitalDto, Long> {
    public HospitalController(CmdbCrudApplicationService<Hospital, HospitalDto, Long> service) {
        super(service);
    }
}

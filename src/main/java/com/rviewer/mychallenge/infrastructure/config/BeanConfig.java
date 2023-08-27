package com.rviewer.mychallenge.infrastructure.config;

import com.rviewer.mychallenge.domain.repository.hospital.HospitalRepository;
import com.rviewer.mychallenge.domain.service.hospital.HospitalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public HospitalService hospitalService(HospitalRepository hospitalRepository) {
        return new HospitalService(hospitalRepository);
    }

}

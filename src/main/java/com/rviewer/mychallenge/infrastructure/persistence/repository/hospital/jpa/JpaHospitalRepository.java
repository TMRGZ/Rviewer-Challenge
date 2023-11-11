package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa;

import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.hospital.HospitalProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.projection.JpaElementProjectableCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHospitalRepository extends JpaElementProjectableCrudRepository<HospitalProjection, HospitalDao, Long> {
}

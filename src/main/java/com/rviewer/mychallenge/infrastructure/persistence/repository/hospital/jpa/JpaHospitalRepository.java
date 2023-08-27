package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa;

import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHospitalRepository extends JpaElementCrudRepository<HospitalDao, Long> {
}

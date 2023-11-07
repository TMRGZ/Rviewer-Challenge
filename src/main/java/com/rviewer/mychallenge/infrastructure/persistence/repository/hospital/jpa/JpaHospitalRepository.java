package com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa;

import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.hospital.HospitalProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.projection.ProjectableElementReadOnlyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHospitalRepository extends JpaElementCrudRepository<HospitalDao, Long>,
        ProjectableElementReadOnlyRepository<HospitalProjection, HospitalDao, Long> {
}

package com.rviewer.mychallenge.infrastructure.persistence.dao.hospital;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import java.util.Collections;
import java.util.List;

@Audited
@Entity
@Table(name = "HOSPITAL")
@Setter
@Getter
@NoArgsConstructor
@FieldNameConstants
@SuperBuilder(toBuilder = true)
public class HospitalDao extends CmdbElementDao<Long> {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(mappedBy = Fields.centralHospital)
    @AuditJoinTable(name = "HOSPITAL_HOSPITAL_AUD")
    private List<HospitalDao> hospitalDelegateList = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "CENTRAL_HOSPITAL_ID", referencedColumnName = "ID")
    private HospitalDao centralHospital;

}

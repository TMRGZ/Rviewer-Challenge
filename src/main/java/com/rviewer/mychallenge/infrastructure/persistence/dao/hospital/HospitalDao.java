package com.rviewer.mychallenge.infrastructure.persistence.dao.hospital;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Audited
@Table(name = "HOSPITAL")
public class HospitalDao extends CmdbElementDao<Long> {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = Fields.centralHospital)
    @AuditJoinTable(name = "HOSPITAL_HOSPITAL_AUD")
    private List<HospitalDao> hospitalDelegateList = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "CENTRAL_HOSPITAL_ID", referencedColumnName = "ID")
    private HospitalDao centralHospital;

}

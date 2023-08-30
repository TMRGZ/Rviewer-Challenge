package com.rviewer.mychallenge.domain.model.hospital;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@FieldNameConstants
@NoArgsConstructor
public class Hospital extends CmdbElement<Long> {

    private String name;

    private String address;

    private Long centralHospitalId;

    private List<Hospital> hospitalDelegateList;

}

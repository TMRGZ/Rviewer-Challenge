package com.rviewer.mychallenge.application.dto.hospital;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Jacksonized
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@FieldNameConstants
public class HospitalDto extends CmdbElementDto<Long> {

    private String name;

    private String address;

    private Long centralHospitalId;

    private List<HospitalDto> hospitalDelegateList;
}

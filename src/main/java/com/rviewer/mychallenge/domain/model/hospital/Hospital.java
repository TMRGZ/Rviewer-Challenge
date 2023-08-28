package com.rviewer.mychallenge.domain.model.hospital;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Hospital extends CmdbElement<Long> {

    private String name;

    private String address;

    private Long centralHospital;

    private List<Hospital> hospitalDelegateList;

}

package com.rviewer.mychallenge.application.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class CmdbElementDto<I> {

    private I id;

}

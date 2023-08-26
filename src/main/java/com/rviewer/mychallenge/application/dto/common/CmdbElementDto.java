package com.rviewer.mychallenge.application.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public abstract class CmdbElementDto<I> {

    private I id;

}

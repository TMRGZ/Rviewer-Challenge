package com.rviewer.mychallenge.domain.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class CmdbElement<I> {

    private I id;

}

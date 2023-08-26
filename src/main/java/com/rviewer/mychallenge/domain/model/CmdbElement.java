package com.rviewer.mychallenge.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public abstract class CmdbElement<I> {

    private I id;

}

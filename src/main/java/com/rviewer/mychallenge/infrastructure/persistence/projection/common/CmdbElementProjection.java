package com.rviewer.mychallenge.infrastructure.persistence.projection.common;

import java.io.Serializable;

public interface CmdbElementProjection<I extends Serializable> {

    I id();
}

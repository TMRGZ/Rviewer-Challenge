package com.rviewer.mychallenge.domain.repository.common.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;

import java.io.Serializable;

public interface ReactiveElementProjectableCrudRepository<
        E extends CmdbElement<I>,
        I extends Serializable>
        extends ReactiveElementProjectableReadOnlyRepository<E, I>, ReactiveElementCrudRepository<E, I> {
}

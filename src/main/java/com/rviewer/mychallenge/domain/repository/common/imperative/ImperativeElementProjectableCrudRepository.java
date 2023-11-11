package com.rviewer.mychallenge.domain.repository.common.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;

public interface ImperativeElementProjectableCrudRepository<E extends CmdbElement<I>, I>
        extends ImperativeElementProjectableReadOnlyRepository<E, I>, ImperativeElementCrudRepository<E, I> {
}

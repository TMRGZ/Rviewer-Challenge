package com.rviewer.mychallenge.domain.repository.common.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;

public interface ImperativeElementCrudRepository<E extends CmdbElement<I>, I> extends ImperativeElementReadOnlyRepository<E, I> {

    E save(E element);

    void delete(E element);

}

package com.rviewer.mychallenge.domain.repository.common;

import com.rviewer.mychallenge.domain.model.CmdbElement;

public interface ElementCrudRepository<E extends CmdbElement<I>, I> extends ElementReadOnlyRepository<E, I> {

    E save(E element);

    void delete(E element);

}

package com.rviewer.mychallenge.domain.repository.common.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface ReactiveElementProjectableCrudRepository<
        E extends CmdbElement<I>,
        I extends Serializable>
        extends ReactiveElementProjectableReadOnlyRepository<E, I>, ReactiveElementCrudRepository<E, I> {

    Mono<E> save(E element, CmdbRetrieveType retrieveType);
}

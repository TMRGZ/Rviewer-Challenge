package com.rviewer.mychallenge.domain.repository.common.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface ReactiveElementProjectableReadOnlyRepository<
        E extends CmdbElement<I>,
        I extends Serializable>
        extends ReactiveElementReadOnlyRepository<E, I> {

    @Override
    default Mono<E> findById(I id) {
        return findById(id, CmdbRetrieveType.COMPLETE);
    }

    Mono<E> findById(I id, CmdbRetrieveType type);

//    @Override
//    default Flux<E> findAll() {
//        return findAll(CmdbRetrieveType.COMPLETE);
//    }
//
//    Flux<E> findAll(CmdbRetrieveType type);
}

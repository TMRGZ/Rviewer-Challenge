package com.rviewer.mychallenge.domain.repository.common.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveElementReadOnlyRepository<E extends CmdbElement<I>, I> {

    Mono<E> findById(I id);

    Flux<E> findAll();

    Flux<E> findHistory(I id);

}

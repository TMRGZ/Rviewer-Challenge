package com.rviewer.mychallenge.domain.repository.common.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import reactor.core.publisher.Mono;

public interface ReactiveElementCrudRepository<E extends CmdbElement<I>, I> extends ReactiveElementReadOnlyRepository<E, I> {

    Mono<E> save(E element);

    Mono<Void> delete(E element);

}

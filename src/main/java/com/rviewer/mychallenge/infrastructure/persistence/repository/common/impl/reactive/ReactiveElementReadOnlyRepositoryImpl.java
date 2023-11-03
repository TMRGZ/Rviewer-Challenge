package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementReadOnlyRepository;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementReadOnlyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.Serializable;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class ReactiveElementReadOnlyRepositoryImpl<
        E extends CmdbElement<I>,
        I extends Serializable>
        implements ReactiveElementReadOnlyRepository<E, I> {

    private final ImperativeElementReadOnlyRepository<E, I> repository;

    @Override
    public Mono<E> findById(I id) {
        return Mono.fromCallable(() -> repository.findById(id))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<E> findAll() {
        return Mono.fromCallable(repository::findAll)
                .flatMapIterable(Function.identity())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<E> findHistory(I id) {
        return Mono.fromCallable(() -> repository.findHistory(id))
                .flatMapIterable(Function.identity())
                .subscribeOn(Schedulers.boundedElastic());
    }
}

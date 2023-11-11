package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementCrudRepository;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementProjectableCrudRepository;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementProjectableCrudRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.Serializable;

public abstract class ReactiveElementProjectableCrudRepositoryImpl<
        E extends CmdbElement<I>,
        I extends Serializable>
        extends ReactiveElementProjectableReadOnlyRepositoryImpl<E, I>
        implements ReactiveElementProjectableCrudRepository<E, I> {

    private final ImperativeElementCrudRepository<E, I> repository;

    protected ReactiveElementProjectableCrudRepositoryImpl(
            ImperativeElementProjectableCrudRepository<E, I> repository
    ) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Mono<E> save(E element) {
        return Mono.fromCallable(() -> repository.save(element))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> delete(E element) {
        return Mono.fromRunnable(() -> repository.delete(element))
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}

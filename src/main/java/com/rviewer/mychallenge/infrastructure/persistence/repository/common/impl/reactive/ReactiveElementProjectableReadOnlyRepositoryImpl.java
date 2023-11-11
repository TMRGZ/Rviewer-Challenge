package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.reactive;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementProjectableReadOnlyRepository;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementProjectableReadOnlyRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.Serializable;

public abstract class ReactiveElementProjectableReadOnlyRepositoryImpl<
        E extends CmdbElement<I>,
        I extends Serializable>
        extends ReactiveElementReadOnlyRepositoryImpl<E, I> implements ReactiveElementProjectableReadOnlyRepository<E, I> {

    private final ImperativeElementProjectableReadOnlyRepository<E, I> repository;

    protected ReactiveElementProjectableReadOnlyRepositoryImpl(ImperativeElementProjectableReadOnlyRepository<E, I> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Mono<E> findById(I id, CmdbRetrieveType type) {
        return Mono.fromCallable(() -> repository.findById(id, type))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(Schedulers.boundedElastic());
    }

//    @Override
//    public Flux<E> findAll(CmdbRetrieveType type) {
//        return Mono.fromCallable(() -> repository.findAll(type))
//                .flatMapIterable(Function.identity())
//                .subscribeOn(Schedulers.boundedElastic());
//    }
}

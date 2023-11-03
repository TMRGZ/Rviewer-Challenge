package com.rviewer.mychallenge.domain.service.common;


import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementCrudRepository;
import reactor.core.publisher.Mono;

public abstract class CmdbCrudService<E extends CmdbElement<I>, I> extends CmdbReadOnlyService<E, I> {

    private final ReactiveElementCrudRepository<E, I> repository;

    protected CmdbCrudService(ReactiveElementCrudRepository<E, I> repository) {
        super(repository);
        this.repository = repository;
    }

    public Mono<E> save(E element) {
        return repository.save(element);
    }

    public Mono<Void> delete(I id) {
        return repository.findById(id).flatMap(repository::delete);
    }
}
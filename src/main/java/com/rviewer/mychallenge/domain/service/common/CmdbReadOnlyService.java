package com.rviewer.mychallenge.domain.service.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.reactive.ReactiveElementReadOnlyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class CmdbReadOnlyService<E extends CmdbElement<I>, I> {

    private final ReactiveElementReadOnlyRepository<E, I> repository;

    public Mono<E> getById(I id) {
        return repository.findById(id);
    }

    public Flux<E> getAll() {
        return repository.findAll();
    }

    public Flux<E> getHistory(I id) {
        return repository.findHistory(id);
    }
}

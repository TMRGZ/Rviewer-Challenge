package com.rviewer.mychallenge.application.service.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.service.common.CmdbReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class CmdbReadOnlyApplicationService<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    final GenericDtoMapper<M, D, I> mapper;
    private final CmdbReadOnlyService<M, I> service;

    public Mono<ResponseEntity<Flux<D>>> getAllElements() {
        Flux<D> elementFluxDto = service.getAll()
                .map(mapper::mapToDto);

        return Mono.just(ResponseEntity.ok(elementFluxDto));
    }

    public Mono<ResponseEntity<D>> getElement(I id) {
        return service.getById(id)
                .map(mapper::mapToDto)
                .map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<Flux<D>>> getElementHistory(I id) {
        Flux<D> historyFlux = service.getHistory(id)
                .map(mapper::mapToDto);
        return Mono.just(ResponseEntity.ok(historyFlux));
    }
}

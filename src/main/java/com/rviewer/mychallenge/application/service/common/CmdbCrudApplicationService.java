package com.rviewer.mychallenge.application.service.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public abstract class CmdbCrudApplicationService<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I>
        extends CmdbReadOnlyApplicationService<M, D, I> {

    private final CmdbCrudService<M, I> service;

    protected CmdbCrudApplicationService(GenericDtoMapper<M, D, I> mapper, CmdbCrudService<M, I> service) {
        super(mapper, service);
        this.service = service;
    }


    public Mono<ResponseEntity<D>> saveElement(Mono<D> elementToSave) {
        return elementToSave.map(mapper::mapToModel)
                .flatMap(service::save)
                .map(mapper::mapToDto)
                .map(maped -> ResponseEntity.status(HttpStatus.CREATED).body(maped));
    }

    public Mono<ResponseEntity<Void>> deleteElement(I id) {
        return service.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}

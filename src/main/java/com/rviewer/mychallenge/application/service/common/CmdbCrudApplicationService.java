package com.rviewer.mychallenge.application.service.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class CmdbCrudApplicationService<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I>
        extends CmdbReadOnlyApplicationService<M, D, I> {

    private final CmdbCrudService<M, I> service;

    public CmdbCrudApplicationService(GenericDtoMapper<M, D, I> mapper, CmdbCrudService<M, I> service) {
        super(mapper, service);
        this.service = service;
    }


    public ResponseEntity<D> saveElement(D elementToSave) {
        M savedElement = service.save(mapper.mapToModel(elementToSave));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDto(savedElement));
    }

    public ResponseEntity<Void> deleteElement(I id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

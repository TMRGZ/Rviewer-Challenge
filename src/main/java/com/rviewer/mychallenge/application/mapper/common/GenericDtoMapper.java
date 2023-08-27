package com.rviewer.mychallenge.application.mapper.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import org.mapstruct.MapperConfig;

@MapperConfig
public interface GenericDtoMapper<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    M mapToModel(D dto);

    D mapToDto(M model);

}

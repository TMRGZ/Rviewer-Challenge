package com.rviewer.mychallenge.infrastructure.persistence.mapper.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import org.mapstruct.Mapper;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public interface GenericDaoMapper<M extends CmdbElement<I>, D extends CmdbElementDao<I>, I extends Serializable> {

    D mapToDao(M model);

    M mapToModel(D dao);

}

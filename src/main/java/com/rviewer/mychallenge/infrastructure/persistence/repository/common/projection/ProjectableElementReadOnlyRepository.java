package com.rviewer.mychallenge.infrastructure.persistence.repository.common.projection;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.common.CmdbElementProjection;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface ProjectableElementReadOnlyRepository<
        P extends CmdbElementProjection<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends Repository<D, I> {

    <R extends P> Optional<R> findById(I id, Class<R> projection);
}

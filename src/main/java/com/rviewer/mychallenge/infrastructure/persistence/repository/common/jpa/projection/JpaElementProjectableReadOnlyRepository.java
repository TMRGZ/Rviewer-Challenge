package com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.projection;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.common.CmdbElementProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementReadOnlyRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface JpaElementProjectableReadOnlyRepository<
        P extends CmdbElementProjection<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends JpaElementReadOnlyRepository<D, I> {

    <R extends P> Optional<R> findById(I id, Class<R> projection);

    <R extends P> List<R> findAllBy(Class<R> projection);
}

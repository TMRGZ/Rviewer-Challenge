package com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.history.RevisionRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface JpaElementReadOnlyRepository<D extends CmdbElementDao<I>, I extends Serializable>
        extends Repository<D, I>, RevisionRepository<D, I, Integer> {

    Optional<D> findById(I id);

    List<D> findAll();

}

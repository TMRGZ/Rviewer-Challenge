package com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface JpaElementCrudRepository<E extends CmdbElementDao<I>, I extends Serializable>
        extends JpaElementReadOnlyRepository<E, I>, JpaRepository<E, I> {
}

package com.lbc.mo.dao;

import com.lbc.mo.entity.AppState;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppStateRepository extends CrudRepository<AppState, Integer>, JpaSpecificationExecutor<AppState> {

}

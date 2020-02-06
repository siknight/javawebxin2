package com.lisi.dao;

import com.lisi.domain.ComMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComManDao  extends JpaRepository<ComMan,Long>,JpaSpecificationExecutor<ComMan> {
}

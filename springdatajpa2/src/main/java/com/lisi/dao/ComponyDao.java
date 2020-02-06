package com.lisi.dao;

import com.lisi.domain.Compony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComponyDao extends JpaRepository<Compony,Long>,JpaSpecificationExecutor<Compony> {
}

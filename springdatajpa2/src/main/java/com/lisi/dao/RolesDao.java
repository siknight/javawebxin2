package com.lisi.dao;

import com.lisi.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolesDao extends JpaRepository<Roles,Long>,JpaSpecificationExecutor<Roles> {
}

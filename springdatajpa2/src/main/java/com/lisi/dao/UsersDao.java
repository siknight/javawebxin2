package com.lisi.dao;

import com.lisi.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersDao extends JpaRepository<Users,Long>,JpaSpecificationExecutor<Users> {
}

package com.lisi.dao;

import com.lisi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {

    @Query("from Customer ")
    public List<Customer> findAllCustomers();

    @Query(value ="from Customer where custName = ?1")
    public List<Customer> findByname(String name);

    @Query(value ="from Customer where custName like ?1")
    public List<Customer> findBynamelike(String name);


    public List<Customer> findCustomersByCustNameIsLike(String name);

    @Query("update Customer set custName = ?1 where custId =?2")
    @Modifying
    public void updateById(String name,Long id);


    @Query("delete from Customer where id=?1")
    @Modifying
    public void delByid(Long id);

}

package com.test;


import com.domain.Customer;
import com.util.JpaUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("all")
public class JpqlTest {


    /**
     * 工具类根据id查询
     */
    @Test
    public  void testfindAll(){
        EntityManager entityManager = JpaUtils.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query from_customer = entityManager.createQuery("from Customer");

        List resultList = from_customer.getResultList();

        for (Object object:resultList ){
            System.out.println(object);
        }


        transaction.commit();
    }

}

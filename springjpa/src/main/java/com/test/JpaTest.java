package com.test;


import com.domain.Customer;
import com.util.JpaUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
@SuppressWarnings("all")
public class JpaTest {
    @Test
    public void test01(){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");

        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();

        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");

        entityManager.persist(customer);



        transaction.commit();
        factory.close();

    }

    /**
     * 工具类修改
     */
    @Test
    public  void testSave(){
        EntityManager entityManager = JpaUtils.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1L);
        customer.setCustName("hahalisi");


        entityManager.merge(customer);

        transaction.commit();
    }

    /**
     * 工具类修改
     */
    @Test
    public  void testUpdate(){
        EntityManager entityManager = JpaUtils.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setCustAddress("重庆");
        customer.setCustName("jiangdihong");
        customer.setCustIndustry("重庆万达有限公司");
        customer.setCustPhone("13132112358");
        customer.setCustLevel("1");

        entityManager.persist(customer);

        transaction.commit();
    }

    /**
     * 工具类删除
     */
    @Test
    public  void testDel(){
        EntityManager entityManager = JpaUtils.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1L);



        entityManager.remove(customer);

        transaction.commit();
    }

    /**
     * 工具类根据id查询
     */
    @Test
    public  void testfindByid(){
        EntityManager entityManager = JpaUtils.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 2L);

        System.out.println(customer);

        entityManager.merge(customer);

        transaction.commit();
    }

}

package com.lisi.test;

import com.lisi.dao.CustomerDao;
import com.lisi.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao dao;

    /**
     * 保存
     */
    @Test
    public void test01(){
        Customer customer = new Customer();
        customer.setCustName("传智播客啦啦啦");
        dao.save(customer);
    }

    /**
     * 修改
     */
    @Test
    public void test02(){

        Customer customer1 = dao.findOne(3L);
        customer1.setCustAddress("hahhaha");
        dao.save(customer1);
    }


    /**
     * 修改
     */
    @Test
    public void test3(){

        Customer customer1 = dao.findOne(3L);
        System.out.println(customer1);
    }


    /**
     * 通过sql查询全部
     */
    @Test
    public void test4(){

//        List<Customer> allCustomers = dao.findAllCustomers();
//
//       for (Customer c:allCustomers){
//           System.out.println(c);
//       }

//        List<Customer> customers = dao.findByname("传智播客啦啦啦");
//
//        System.out.println(customers);

//        List<Customer> bynamelike = dao.findBynamelike("%播客%");
//
//        System.out.println(bynamelike);

        List<Customer> customers = dao.findCustomersByCustNameIsLike("%播客%");

        System.out.println(customers);


    }


    /**
     * 通过id 修改
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void test5(){
        dao.updateById("dasi",3L);
    }

    /**
     * 删除
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testdel(){
        dao.delByid(3L);
    }
}

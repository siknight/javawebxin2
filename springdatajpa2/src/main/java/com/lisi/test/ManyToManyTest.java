package com.lisi.test;

import com.lisi.dao.RolesDao;
import com.lisi.dao.UsersDao;
import com.lisi.domain.Roles;
import com.lisi.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private RolesDao rolesDao;
    @Autowired
    private UsersDao usersDao;

    @Test
    @Transactional
    @Rollback(false)
    public void test01(){
        Roles roles = new Roles();
        roles.setRoleName("学生2");

        Users users = new Users();
        users.setUserName("xiaosisi");


        roles.getUsers().add(users);

        users.getRoles().add(roles);

        rolesDao.save(roles);

        usersDao.save(users);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void test02(){
        rolesDao.delete(2L);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void test03(){

    }


}

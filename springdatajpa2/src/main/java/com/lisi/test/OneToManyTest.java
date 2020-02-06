package com.lisi.test;

import com.lisi.dao.ComManDao;
import com.lisi.dao.ComponyDao;
import com.lisi.domain.ComMan;
import com.lisi.domain.Compony;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    ComManDao comManDao;
    @Autowired
    ComponyDao componyDao;

    /**
     * 保存
     */
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void test01(){
        ComMan comMan = new ComMan();
        comMan.setLkmName("xiaosisi6");

        Compony compony = new Compony();
        compony.setCustName("天津有限公司5");
        compony.setCustId(5L);

        comMan.setCompony(compony);
        componyDao.save(compony); //先保存一的一方 只用两条插入语句
        comManDao.save(comMan);
    }


    /**
     * 删除
     */
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void testquery01(){
//       comManDao.delete(4L);
        componyDao.delete(6L);
    }

    /**
     * 导航查询
     */
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void testquery(){
        Compony compony = componyDao.findOne(5L);

        Set<ComMan> comMans = compony.getComMans();

        for (ComMan man:comMans){
            System.out.println(man);
        }

    }
}

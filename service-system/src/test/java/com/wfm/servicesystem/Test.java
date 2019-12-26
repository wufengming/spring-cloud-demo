package com.wfm.servicesystem;

import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.mapper.UserMapper;
import com.wfm.servicesystem.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * description: Test
 * date: 2019-11-06 19:23
 * author: wfm
 * version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void  adduser(){
        UserEntity user=new UserEntity();
        user.setAccount("001");
        user.setBirthday(new Date());
        user.setCardNumber("12321312");

        user.setCreator(0L);
        user.setEditor(0L);
        user.setInsertDt(new Date());
        user.setUpdateDt(new Date());
        user.setCurOrgid(0L);
        user.setRecordVer(0);

        userMapper.insert(user);

    }

    @org.junit.Test
    public void  changeBase(){

       UserEntity user= userService.queryUserById(1L);

       System.out.println(user);

       UserEntity user2=userService.findByUserName("admin");

        System.out.println(user2);

    }
}

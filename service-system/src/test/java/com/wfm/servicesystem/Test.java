package com.wfm.servicesystem;

import com.wfm.servicecommon.utils.JdbcUtil;
import com.wfm.servicesystem.entity.UserEntity;
import com.wfm.servicesystem.mapper.UserMapper;
import com.wfm.servicesystem.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.util.*;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @org.junit.Test
    public void getAllUser() {
        //List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from s_user");
        List<UserEntity> list = jdbcTemplate.query("select * from s_user", new BeanPropertyRowMapper<>(UserEntity.class));

        System.out.println(list.size());
    }

    @org.junit.Test
    public void testResultRecord() throws Exception {
        String sql = "select * from t_user";
//        List<Object> params = new ArrayList<Object>();
//        params.add(4363087427670016L);

        // List<HashMap<String,Object>> user = JdbcUtil.getListHashMap(sql, null );


        JdbcUtil jdbc=new JdbcUtil("db2");
        List<UserEntity> list = jdbc.getListCommBean(UserEntity.class, sql, null);

        System.out.println(list);
        System.out.println(list.size());
    }
}

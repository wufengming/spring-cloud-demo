package com.wfm.servicesystem.redis;

import com.wfm.servicecommon.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: RedisTest
 * date: 2019-12-25 14:07
 *
 * @author: wfm
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisIsOk() {
        boolean res = redisUtil.checkConnection();
        if (res) {
            System.out.println("redis缓存有效！" + res);
        } else {
            System.out.println("redis缓存失败！" + res);
        }
    }
}

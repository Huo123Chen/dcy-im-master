package com.dcy.router.controller;

import com.dcy.common.utils.BPwdEncoderUtil;
import com.dcy.router.model.User;
import com.dcy.router.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.dcy.common.constant.CommonConstant.USER_ONLINE;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void addUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            users.add(new User().setUsername("lisi" + i).setPassword(BPwdEncoderUtil.BCryptPassword("123456")).setPhone("13812341234"));
        }
        iUserService.saveBatch(users);
    }

    @Test
    public void redisSetTest() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();

//        opsForSet.add(USER_ONLINE, "1", "2", "3");

        Set<Object> members = opsForSet.members(USER_ONLINE);
        opsForSet.add(USER_ONLINE, members, "4");
        System.out.println(opsForSet.isMember(USER_ONLINE, "1"));
    }

    @Test
    public void redisHashTest() {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();

        opsForHash.put(USER_ONLINE, "1", "1");
        opsForHash.put(USER_ONLINE, "2", "2");
        opsForHash.put(USER_ONLINE, "3", "3");


        System.out.println(opsForHash.hasKey(USER_ONLINE,"1"));
        System.out.println(opsForHash.hasKey(USER_ONLINE,"4"));


        System.out.println(opsForHash.get(USER_ONLINE,"1"));
        System.out.println();


        opsForHash.delete(USER_ONLINE,"2");

        opsForHash.keys(USER_ONLINE);
        opsForHash.values(USER_ONLINE);
    }
}
package com.qiancheng.redis;

import com.alibaba.fastjson.JSON;
import com.qiancheng.redis.practice.reids.RedisKeyUtil;
import com.qiancheng.redis.practice.reids.RedisService;
import com.qiancheng.redis.practice.vo.UserVo;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${redis.msg.topic}")
    private String myTopic;
    @Value("${redis.zset.delay-order.key}")
    private String delayOrderKey;

    //    @Resource
//    private ValueOperations<String, Object> valueOperations;
//
//    @Autowired
//    private HashOperations<String, String, Object> hashOperations;
//
//    @Autowired
//    private ListOperations<String, Object> listOperations;
//
//    @Autowired
//    private SetOperations<String, Object> setOperations;
//
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
//
//    @Resource
//    private RedisService redisService;

    @Test
    public void testZSet() {
        Double now = new Double(System.currentTimeMillis() +1000*60*60);
        Set<ZSetOperations.TypedTuple<Object>> orderNosRes = zSetOperations.rangeByScoreWithScores(delayOrderKey, 0,now );
        for (ZSetOperations.TypedTuple<Object> eachOrder : orderNosRes) {
            String orderNo = (String) eachOrder.getValue();
            System.out.println(now + "|" + orderNo + "|" + eachOrder.getScore());

        }
    }

//    @Test
//    public void testObj() throws Exception {
//        UserVo userVo = new UserVo();
//        userVo.setAddress("上海2");
//        userVo.setName("测试dfas");
//        userVo.setAge(123);
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        redisService.expireKey("name", 20, TimeUnit.SECONDS);
//        String key = RedisKeyUtil.getKey(UserVo.Table, "name", userVo.getName());
//        UserVo vo = (UserVo) operations.get(key);
//        System.out.println(vo);
//    }

    @Test
    public void testValueOption() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setAddress("beijing");
        userVo.setName("qiancheng");
        userVo.setAge(23);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String key = "qc";
        valueOperations.set(key, userVo);

        System.out.println(JSON.toJSONString(valueOperations.get(key)));
        UserVo vo = (UserVo) valueOperations.get(key);
        System.out.println(JSON.toJSONString(vo));
    }

//    @Test
//    public void testSetOperation() throws Exception {
//        UserVo userVo = new UserVo();
//        userVo.setAddress("北京");
//        userVo.setName("jantent");
//        userVo.setAge(23);
//        UserVo auserVo = new UserVo();
//        auserVo.setAddress("n柜昂周");
//        auserVo.setName("antent");
//        auserVo.setAge(23);
//        setOperations.add("user:test", userVo, auserVo);
//        Set<Object> result = setOperations.members("user:test");
//        System.out.println(result);
//    }
//
//    @Test
//    public void HashOperations() throws Exception {
//        UserVo userVo = new UserVo();
//        userVo.setAddress("北京");
//        userVo.setName("jantent");
//        userVo.setAge(23);
//        hashOperations.put("hash:user", userVo.hashCode() + "", userVo);
//        System.out.println(hashOperations.get("hash:user", userVo.hashCode() + ""));
//    }
//
//    @Test
//    public void ListOperations() throws Exception {
//        UserVo userVo = new UserVo();
//        userVo.setAddress("北京");
//        userVo.setName("jantent");
//        userVo.setAge(23);
////        listOperations.leftPush("list:user",userVo);
////        System.out.println(listOperations.leftPop("list:user"));
//        // pop之后 值会消失
//        System.out.println(listOperations.leftPop("list:user"));
//    }

}

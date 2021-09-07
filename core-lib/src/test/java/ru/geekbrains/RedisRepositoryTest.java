package ru.geekbrains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import ru.geekbrains.corelib.configurations.rediseBean.RediseDB;
import ru.geekbrains.corelib.repository.RedisRepository;

@SpringBootTest(classes = {RedisRepository.class, RediseDB.class})
public class RedisRepositoryTest {
    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private RediseDB rediseJP;

    public final static Long timeLife = 1L;
    public final static String token = "testToken dcWKASDCMV'wkasmv'";


    @BeforeEach
    @AfterEach
    public void myFlushDB(){
        try(Jedis jedis = rediseJP.getResource()){
            jedis.flushDB();
        }
    }



    @Test
    public void timeLifeNewDataFromDBShouldBeLimited(){
        redisRepository.saveToken(token,timeLife);
        Assertions.assertTrue(findData(token));
    }

    @Test
    public void checkTimeLife(){
        redisRepository.saveToken(token,timeLife);
        try {
            Thread.sleep(timeLife*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(findData(token));
    }

    @Test
    public void checkFindTokenShouldBeFalse(){
        Assertions.assertFalse(redisRepository.findToken(token));
    }

    @Test
    public void checkFindTokenShouldBeTrue(){
        try(Jedis jedis = rediseJP.getResource()){
            jedis.set(token,"");
        }
        Assertions.assertTrue(redisRepository.findToken(token));
    }

    @Test
    public void checkDeleteTokenShouldBeFalse(){
        try(Jedis jedis = rediseJP.getResource()){
            jedis.set(token,"");
        }
        redisRepository.deleteToken(token);
        Assertions.assertFalse(findData(token));
    }



    private boolean findData(String key){
        try(Jedis jedis = rediseJP.getResource()){
            return jedis.exists(key);
        }
    }

    private void deleteData(String key){
        try(Jedis jedis = rediseJP.getResource()){
            jedis.del(key);
        }
    }




}

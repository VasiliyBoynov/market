package ru.geekbrains.corelib.repository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import ru.geekbrains.corelib.configurations.rediseBean.RediseDB;

@Component
public class RedisRepository {
    @Autowired
    private RediseDB rediseJP;

    public void saveToken(String token, long timeLife){
        try(Jedis jedis = rediseJP.getResource()){
            jedis.setex(token,timeLife,"");
            /*Transaction t = jedis.multi();
            jedis.set(token,"");
            jedis.expire(token,timeLife);
            t.exec();*/
        }
    }

    public boolean findToken(String token){
        try(Jedis jedis = rediseJP.getResource()){
            return jedis.exists(token);
        }
    }

    public void  deleteToken(String token){
        try(Jedis jedis = rediseJP.getResource()){
            jedis.del(token);
        }
    }

}

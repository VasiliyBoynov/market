package ru.geekbrains.corelib.configurations.rediseBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RediseDB extends JedisPool {
}

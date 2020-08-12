package com.demo;

import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author:
 * @date: created in 10:26 2020/8/12
 * @version:
 */
public class RedisDemo {
    private static ShardedJedisPool jedisPool;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        /*        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);*/
        JedisShardInfo jedisShardInfo = new JedisShardInfo("120.79.213.127", 6379);
        jedisShardInfo.setPassword("547717253");
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo);
        jedisPool = new ShardedJedisPool(jedisPoolConfig, list);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = jedisPool.getResource();
        for (int i = 0; i < 10; i++) {
            jedis.set("test" + i, "00" + i);
            String testValue = jedis.get("test" + i);
            System.out.println(testValue);
        }
    }
}
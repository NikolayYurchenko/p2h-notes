package com.p2h.notes.data.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

@Slf4j
public class AbstractRedisRepository {

    protected final String prefix;

    protected final RedisTemplate<String, Boolean> redisTemplate;

    public AbstractRedisRepository(RedisTemplate<String, Boolean> redisTemplate, String prefix) {

        this.prefix = prefix;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Set flag
     * @param key
     * @param flag
     */
    public void setFlag(String key, boolean flag) {

        log.debug("Adding entry, key:[{}] and value:[{}]", key, flag);

        redisTemplate.opsForValue().set(key, flag);
    }


    public boolean getFlagByKey(String key) {

        Optional<Boolean> value = Optional.ofNullable(redisTemplate.opsForValue().get(key));

        return value.orElse(false);
    }

    protected String fullName(String name) {

        return this.prefix + ":" + name;
    }
}


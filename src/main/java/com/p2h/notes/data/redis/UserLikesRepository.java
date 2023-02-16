package com.p2h.notes.data.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserLikesRepository extends AbstractRedisRepository {

    public UserLikesRepository(RedisTemplate<String, Boolean> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * Remember that specific user like specific note
     * @param userUid
     * @param noteUid
     */
    public void rememberLikeForNote(String userUid, String noteUid) {

        String key = userUid + ":" + noteUid;

        super.setFlag(key, true);
    }

    /**
     * Forget(dislike maybe) that specific user like specific note
     * @param userUid
     * @param noteUid
     */
    public void forgetLikeForNote(String userUid, String noteUid) {

        String key = userUid + ":" + noteUid;

        super.setFlag(key, false);
    }

    /**
     * Is user set like for this note
     * @param userUid
     * @param noteUid
     * @return
     */
    public boolean isUserLikeThisNote(String userUid, String noteUid) {

        String key = userUid + ":" + noteUid;

        return super.getFlagByKey(key);
    }
}

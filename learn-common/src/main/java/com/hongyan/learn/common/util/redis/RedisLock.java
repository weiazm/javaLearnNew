package com.hongyan.learn.common.util.redis;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现Redis分布式锁，依赖RedisUtil.
 * <p>
 * 实现了{@code java.util.concurrent.locks.Lock}接口，未实现lockInterruptibly和newCondition方法
 * </p>
 * 
 * @title RedisLock
 * @desc
 * @author purerboy
 * @date 2015年9月6日
 * @version 1.0
 */
@Slf4j
public class RedisLock extends RedisBaseLock {

    public RedisLock(RedisUtil redisUtil, String key) {
        this(redisUtil, key, key, DEF_LOCK_EXPIRE);
    }

    public RedisLock(RedisUtil redisUtil, String key, String owner) {
        this(redisUtil, key, owner, DEF_LOCK_EXPIRE);
    }

    public RedisLock(RedisUtil redisUtil, String key, String owner, int lockExpire) {
        super(redisUtil, key, owner, lockExpire);
    }

    @Override
    public Boolean unlock(final String key, final LockObj value, final boolean force) throws Exception {
        boolean succ = synAct(new RedisLockAction<Boolean>() {

            @Override
            public Boolean doAction(RedisUtil redisUtil) throws Exception {
                if (redisUtil.exists(key) == false) {
                    return true;
                }
                LockObj last = decodeLock(redisUtil.get(key));
                if (force || last == null || last.equals(value)) {
                    redisUtil.del(key);
                    log.debug("unlock, key[{}], value[{}], last[{}]", key, value, last);
                    return true;
                } else {
                    log.debug("unlock fail, key[{}], value[{}], last[{}]", key, value, last);
                    return false;
                }
            }

        }, false);
        if (succ && this.value != null) {
            this.value = null;
        }
        return succ;
    }

}

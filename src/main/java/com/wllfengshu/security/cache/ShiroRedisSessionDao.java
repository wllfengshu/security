package com.wllfengshu.security.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 存储Session
 *
 * @author maoxs
 */
@Slf4j
public class ShiroRedisSessionDao extends AbstractSessionDAO {

    private RedisTemplate redisTemplate;

    public ShiroRedisSessionDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null){
            throw new UnknownSessionException("session cannot be null.");
        }
        log.debug("更新session,id=[{}]", session.getId().toString());
        try {
            redisTemplate.opsForValue().set(session.getId().toString(), session, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Session session) {
        if (session == null){
            throw new UnknownSessionException("session cannot be null.");
        }
        log.info("删除session,id=[{}]", session.getId().toString());
        try {
            String key = session.getId().toString();
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.debug("获取存活的session");
        return Collections.emptySet();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        log.info("创建session,id=[{}]", session.getId().toString());
        try {
            redisTemplate.opsForValue().set(session.getId().toString(), session, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.debug("获取session,id=[{}]", sessionId.toString());
        Session readSession = null;
        try {
            readSession = (Session) redisTemplate.opsForValue().get(sessionId.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return readSession;
    }

}
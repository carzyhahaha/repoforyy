package com.sy.coursechoosing.auth;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class RedisSessionDao extends CachingSessionDAO {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private CacheManager cm=null;

    private int defaultExpireTime = 3600;

    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {
        Serializable sessionId = session.getId();
        cm = CacheManager.create();
        if(cm==null){
            cm = new CacheManager(getCacheManagerConfigFileInputStream());
        }
        Ehcache ehCache = cm.getCache("sessioncache");
        redisTemplate.delete(sessionId.toString());
        ehCache.remove(sessionId.toString());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        cm = CacheManager.create();
        if(cm==null){
            cm = new CacheManager(getCacheManagerConfigFileInputStream());
        }
        Ehcache ehCache = cm.getCache("sessioncache");
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(sessionId.toString(), session);
        redisTemplate.expire(sessionId.toString(), this.defaultExpireTime, TimeUnit.SECONDS);
        ehCache.put(new Element(sessionId.toString(),session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }

    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache.xml";
        try {
            return ResourceUtils.getInputStreamForPath(configFile);
        } catch (IOException e) {
            throw new ConfigurationException("Unable to obtain input stream for cacheManagerConfigFile [" +
                    configFile + "]", e);
        }
    }
}

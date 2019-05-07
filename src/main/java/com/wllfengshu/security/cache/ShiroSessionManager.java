package com.wllfengshu.security.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * 解决频繁访问redis的问题
 *
 * @author: lianyc
 */
@Slf4j
public class ShiroSessionManager extends DefaultWebSessionManager {

    public ShiroSessionManager(){
        super();
    }

    @Override
    protected Session retrieveSession(SessionKey sessionKey){
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionId == null) {
            log.debug("sessionId can not found from SessionKey:{}", sessionKey);
            return null;
        }
        ServletRequest request = null;
        if(sessionKey instanceof WebSessionKey){
            request = ((WebSessionKey)sessionKey).getServletRequest();
        }
        if (request == null) {
            log.debug("request can not found from SessionKey:{}", sessionKey);
            return null;
        }
        Session session = (Session) request.getAttribute(sessionId.toString());
        if (session == null){
            session = super.retrieveSession(sessionKey);
            request.setAttribute(sessionId.toString(),session);
        }
        return session;
    }
}

package com.wllfengshu.security.config;

import com.wllfengshu.security.cache.ShiroRedisCacheManager;
import com.wllfengshu.security.cache.ShiroRedisSessionDao;
import com.wllfengshu.security.cache.ShiroSessionManager;
import com.wllfengshu.security.shiro.CustomRealm;
import com.wllfengshu.security.utils.CollectionSerializer;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置shiro
 *
 * @author maoxs
 */
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(RedisTemplate redisTemplate, RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getCustomRealm(redisCacheManager));
        securityManager.setCacheManager(getShiroRedisCacheManager(redisCacheManager));
        securityManager.setRememberMeManager(getRememberMeManager());
        securityManager.setSessionManager(getSessionManager(redisTemplate));
        return securityManager;
    }

    /**
     * 缓存管理器的配置
     *
     * @param redisCacheManager
     * @return
     */
    @Bean
    public ShiroRedisCacheManager getShiroRedisCacheManager(RedisCacheManager redisCacheManager) {
        ShiroRedisCacheManager cacheManager = new ShiroRedisCacheManager();
        cacheManager.setCacheManager(redisCacheManager);
        return cacheManager;
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filters = new LinkedHashMap<>();
        filters.put("/login", "anon");
        filters.put("/swagger-ui.html*", "anon");
        filters.put("/webjars/**", "anon");
        filters.put("/v2/**", "anon");
        filters.put("/configuration/**", "anon");
        filters.put("/swagger-resources/**", "anon");
        filters.put("/druid/**", "anon");
        //除了以上的请求外，其它请求都需要登录
        filters.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filters);
        return factoryBean;
    }

    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     * @return
     */
    @Bean
    public CustomRealm getCustomRealm(RedisCacheManager redisCacheManager) {
        CustomRealm realm = new CustomRealm();
        realm.setCachingEnabled(true);
        realm.setCacheManager(getShiroRedisCacheManager(redisCacheManager));
        //认证
        realm.setAuthenticationCachingEnabled(true);
        //授权
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthenticationCacheName("fulinauthen");
        realm.setAuthorizationCacheName("fulinauthor");
        return realm;
    }

    /**
     * 管理shiro bean生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 配置Cookie的生成模版(cookie的name，cookie的有效时间)
     * @return
     */
    @Bean
    public SimpleCookie getRememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间30天 ,单位秒
        cookie.setMaxAge(259200);
        return cookie;
    }

    /**
     * 配置rememberMeManager
     * @return
     */
    @Bean
    public CookieRememberMeManager getRememberMeManager() {
        CookieRememberMeManager meManager = new CookieRememberMeManager();
        meManager.setCookie(getRememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        meManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return meManager;
    }

    /**
     * 配置sessionManager，由redis存储数据
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultWebSessionManager getSessionManager(RedisTemplate redisTemplate) {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        CollectionSerializer<Serializable> collectionSerializer = CollectionSerializer.getInstance();
        redisTemplate.setDefaultSerializer(collectionSerializer);
        //redisTemplate默认采用的其实是valueSerializer
        redisTemplate.setValueSerializer(collectionSerializer);
        ShiroRedisSessionDao redisSessionDao = new ShiroRedisSessionDao(redisTemplate);
        sessionManager.setSessionDAO(redisSessionDao);
        sessionManager.setDeleteInvalidSessions(true);
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("securityCookie");
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    /**
     * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug
     * @return
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setUsePrefix(true);
        return creator;
    }

}

package com.wllfengshu.security.config;

import com.wllfengshu.security.shiro.CustomRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置shiro
 *
 * @author wllfengshu
 */
@Configuration
public class ShiroConfig {

    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     * @return
     */
    @Bean
    public Realm realm() {
        return new CustomRealm();
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        chain.addPathDefinition("/login", "anon");
        chain.addPathDefinition("/swagger-ui.html*", "anon");
        chain.addPathDefinition("/webjars/**", "anon");
        chain.addPathDefinition("/v2/**", "anon");
        chain.addPathDefinition("/configuration/**", "anon");
        chain.addPathDefinition("/swagger-resources/**", "anon");
        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;
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

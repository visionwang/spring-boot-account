package com.yuuyoo.account.authentication;

import com.yuuyoo.account.mobile.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Description:
 * @Auther: dave
 * @Date: 2018/6/8 15:56
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig
    extends ResourceServerConfigurerAdapter {

  @Autowired
  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

  @Autowired
  private AuthorizeConfigManager authorizeConfigManager;

  @Override
  public void configure(HttpSecurity http) throws Exception {


//    http.httpBasic()
//        .and()
//        .authorizeRequests()
//        //.antMatchers("/oauth/authorize", "/oauth/confirm_access","/oauth/token","/manage/*")
//        .antMatchers("/authentication/mobile","/signIn.html")
//        .permitAll()
//        .anyRequest()
//        .authenticated();

    http.apply(smsCodeAuthenticationSecurityConfig);

    authorizeConfigManager.config(http.authorizeRequests());

  }
}

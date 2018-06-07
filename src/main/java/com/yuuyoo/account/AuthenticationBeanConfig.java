/**
 * 
 */
package com.yuuyoo.account;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * 
 * 认证相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 * 
 * @author zhailiang
 *
 */
@Configuration
public class AuthenticationBeanConfig {

//	@Autowired
//	private DataSource dataSource;

	/**
	 * 默认密码处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 默认认证器
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public UserDetailsService userDetailsService() {
		return new DefaultUserDetailsService();
	}



	/**
	 * 默认
	 * @return
	 */
//	@Bean
//	@ConditionalOnMissingBean(PersistentTokenRepository.class)
//	public PersistentTokenRepository persistentTokenRepository(){
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		tokenRepository.setDataSource(dataSource);
//		return tokenRepository;
//	}

	/**
	 * 授权码生成/存储
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(AuthorizationCodeServices.class)
	public AuthorizationCodeServices imoocAuthorizationCodeServices(){
		return new InMemoryAuthorizationCodeServices();
	}
}

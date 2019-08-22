package navin.web.docs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.resourceId(RESOURCE_ID);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*http
			.anonymous().disable()
			.authorizeRequests()
			.antMatchers("/users/**").access("hasRole('EDITOR')")
			.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
		http.authorizeRequests()
			.antMatchers("/api/**").permitAll()
			.anyRequest().authenticated();
	}
}

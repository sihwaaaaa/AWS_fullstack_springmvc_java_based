

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.log4j.Log4j;
@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/sample/all").permitAll()
		.antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')");
		super.configure(http);
	}
	
}

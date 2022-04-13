package kr.co.abandog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.abandog.service.MemberUserDetailService;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MemberUserDetailService memberUserDetailService;
	
	@Autowired
	CustomAuthenticationSuccessHandler customSuccessHandler;
	
    @Bean
    public PasswordEncoder passwordEncoder(){
        //암호화해서 평문과 비교는 할 수 있지만 복호화는 할 수 없는 클래스의 인스턴스를 생성해서 리턴
        return new BCryptPasswordEncoder();
    }
    
	//인가 설정 메서드 오버라이딩
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/","/favicon.ico", "/layout/home", "/image/*", "/list", "/css/*", "/test", "/openapi/*").permitAll() //로그인 여부 와 상관없이 접근 가능
            .antMatchers("/review/*").hasRole("USER") //USER 권한이 있어야 만 접근 가능
        	.anyRequest().authenticated(); //그 외의 요청은 권한이 있으면 허용
        
        //CSRF 토큰 비교하는 작업을 수행하지 않음
        http.csrf().disable();
        
        //커스텀 로그인 설정
        http.formLogin()
        	.loginPage("/login/login")
        	.defaultSuccessUrl("/home")
        	.successHandler(customSuccessHandler)
        	.permitAll();
        
        //로그아웃설정
        http.logout()
        	.logoutSuccessUrl("/login/login");
        
        //외부로그인
        http.oauth2Login()
            .loginPage("/login/login"); 
    }
    
    //인증을 처리
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(memberUserDetailService);
    }
}

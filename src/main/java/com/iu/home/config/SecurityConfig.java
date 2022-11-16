package com.iu.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.iu.home.member.MemberSecurityService;
import com.iu.home.member.MemberSocialService;
import com.iu.home.member.security.LoginFail;
import com.iu.home.member.security.LoginSuccess;
import com.iu.home.member.security.LogoutCustom;
import com.iu.home.member.security.LogoutSuccessCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private LoginSuccess loginSuccess;
	@Autowired
	private LoginFail loginFail;
	@Autowired
	private LogoutCustom logoutCustom;
	@Autowired
	private LogoutSuccessCustom logoutSuccessCustom;
	@Autowired
	private MemberSecurityService memberSecurityService;
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Bean
	// public을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		// Security에서 무시해야하는 URL패턴 등록
		return web -> web.ignoring()
						.antMatchers("/images/**")
						.antMatchers("/css/**")
						.antMatchers("/js/**")
						.antMatchers("/favicon/**")
						.antMatchers("/resources/**");
					
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
		httpSecurity	
					
					.csrf()
					.disable()
					.cors()
					.configurationSource(this.corsConfigurationSource())
					.and()
				.authorizeHttpRequests()							// 요청 URL과 그에 관한 권한 설정
					//.antMatchers("/").permitAll()					// /로 시작하는거 모두 허용 하겠다
					.antMatchers("/admin").hasRole("ADMIN")			//어드민롤 가지면 /admin 통과
					.antMatchers("/manager").hasRole("MANAGER")		//매니저 가지면 /manager
					.antMatchers("/qna/list").permitAll()
					.antMatchers("/qna/**").hasRole("MEMBER")
					.anyRequest().permitAll()
					//.anyRequest().authenticated() 					//롤에 관계없이 로그인 한 사람들
					.and()
				.formLogin()							// 로그인 폼 인증 설정
					.loginPage("/member/login") 		//내장된 로그인폼을 사용하지 않고 개발자가 만든 폼 요청 URL 사용
					//.loginProcessingUrl("login") 		//로그인을 진행 요청할 form태그의 action의 주소 지정
					.passwordParameter("pw")			// 패스워드 파라미터는 password이지만 개발자가 다른 파라미터 이름을 사용할 때 
					.usernameParameter("id")			// id 파라미터는 username이지만 개발자가 다른 파라미터 이름을 사용할 때
					//.defaultSuccessUrl("/") 			//인증에 성공할 경우 요청할 URL
					.successHandler(loginSuccess)
					//.failureUrl("/member/login?error=true&message=LoginFail") 		//인증에 실패한 경우 요청할 URL
					.failureHandler(loginFail)
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")
					//.logoutSuccessUrl("/")
					//.invalidateHttpSession(true)	// 세션 지우기
					.logoutSuccessHandler(logoutSuccessCustom)
					.addLogoutHandler(logoutCustom)
					.deleteCookies("JSESSIONID")	// 쿠키 지우기
					.permitAll()
					.and()
				.rememberMe() 	// RememberMe 설정
					.rememberMeParameter("rememberMe") 	// 파라미터명
					.tokenValiditySeconds(300) 			// 로그인유지 유지시간, 초단위
					.key("rememberMe") 					// 인증받은 사용자의 정보로 token 생성시 필요, 필수값
					.userDetailsService(memberSecurityService) 			// 인증절차를 실행할 UserDetailService, 필수
					.authenticationSuccessHandler(loginSuccess) 		// 로그인 성공 Handler
					.and()
				.oauth2Login()	// Social login 설정
					.userInfoEndpoint()
					.userService(memberSocialService)
					;					
		
		return httpSecurity.build();
	}
	
	// 평문(Clear Text)을 암호화 시키는 객체
	@Bean
	public PasswordEncoder getEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	//@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://192.168.1.11:5500/"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}

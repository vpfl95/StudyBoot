package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iu.home.member.MemberVO;

import lombok.val;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler {
	
	@Value("${my.clientId}")
	private String client_id;
	@Value("${my.redirectUri}")
	private String redirect_uri;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("================ Logout 성공시에만 실행====================");
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal(); 		//memberVO
		String social = memberVO.getSocial();
		
		if(social!=null) {
			if(social.equals("kakao")) {
				try {
					response.sendRedirect("https://accounts.kakao.com/logout?continue=https://developers.kakao.com");
					//response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+client_id+"&logout_redirect_uri="+redirect_uri);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				RestTemplate restTemplate = new RestTemplate();
//				ResponseEntity<String> res = restTemplate.getForEntity("https://developers.kakao.com/logout", null, String.class);
//				log.info("res => {}",res);
			}else if(social.equals("google")) {
				
			}
		}else{			
			response.sendRedirect("/");
		}
	}

}

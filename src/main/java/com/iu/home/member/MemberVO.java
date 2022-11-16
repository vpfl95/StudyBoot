package com.iu.home.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User {
	@NotBlank(message="ID는 꼭 필요해")
	private String id;
	@NotBlank
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
	private String pw;
	@NotBlank
	private String name;
	@Email
	@NotBlank
	private String email;
	private boolean enabled;
	private String pwCheck;
	private List<RoleVO> roleVOs;
	
	
	// ====== Social Login =====
	// Kakao, Naver, Google
	private String social;
	// OAuth2User, Token등 정보 저장
	private Map<String, Object> attributes; 
	
	
	@Override  //권한을 넣어준다.
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// <? super T> T나 T의 부모타입을 담을 수 있다. grantedauthority타입이나 이걸 상속받는 타입 을 담을 수 있다.
		List<GrantedAuthority> authorities= new ArrayList<>();
		for(RoleVO roleVO: roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
			
		}
		return authorities;
	}
	@Override
	public String getPassword() {
		// PW 반환
		return this.pw;
	}
	@Override
	public String getUsername() {
		// ID 반환
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// 계정의 만료 여부
		// true : 만료 안됨
		// false : 만료 됨, 로그인 불가
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부
		// true : 계정이 잠기지 않음
		// false : 계정이 잠김, 로그인 불가
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료 여부
		// true : 만료 안됨
		// false : 만료 됨, 로그인 불가
		return true;
	}
	
	// isEnabled
	// 계정 사용 여부
	// true : 계정 활성화(계정 사용 가능)
	// false : 계정 비활성화(계정 사용 불가능, 로그인 불가)
	public boolean isEnabled() {
		return true;
	}
	
	//OAuth2User
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}
}

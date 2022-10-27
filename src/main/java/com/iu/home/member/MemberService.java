package com.iu.home.member;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	public Integer getIdCheck(MemberVO memberVO)throws Exception{
		return memberDAO.getIdCheck(memberVO);
	}
	
	public int setJoin(MemberVO memberVO)throws Exception{
		int result = memberDAO.setJoin(memberVO);
		
		if(result<1) {
			throw new Exception();
		}
		
		result = memberDAO.setMemberRole(memberVO);
		
		if(result<1) {
			throw new Exception();
		}
		
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		return memberDAO.getLogin(memberVO);
	}
	
	public int setMemberRole(MemberVO memberVO)throws Exception{
		return memberDAO.setMemberRole(memberVO);
	}
}

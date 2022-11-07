package com.iu.home.member;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	
	// 사용자 정의 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check=false;
		//check=false 검증성공(error없음)
		//check=true 검증실패(error 있음)
		
		//1.annotation 검증
		check = bindingResult.hasErrors();
		//2.password가 일치하는지 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			check=true;
			//에러메세지
			//bindingResult.rejectValue("멤버변수명(path)", "properties의 키")
			bindingResult.rejectValue("pwCheck", "member.password.notEqual");
		}
		
		//3. id가 중복 인지 검증
		int result = memberDAO.getIdCheck(memberVO);
		if(result > 0) {
			check=true;
			bindingResult.rejectValue("id","member.id.equal");
		}
		
		return check;
	}
	
	
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
	//로그인 처리는 security에서 처리함
//	public MemberVO getLogin(MemberVO memberVO)throws Exception{
//		return memberDAO.getLogin(memberVO);
//	}
	
	public int setMemberRole(MemberVO memberVO)throws Exception{
		return memberDAO.setMemberRole(memberVO);
	}
}

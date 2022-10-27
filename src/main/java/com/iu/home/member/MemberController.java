package com.iu.home.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberService.getIdCheck(memberVO);
//		if(memberVO==null) {
//			return 0;
//		}else {
//			return 1;
//		}
		
	}
	
	
	
	@GetMapping("join")
	public String setJoin()throws Exception{
		return "/member/join";
	}
	
	@PostMapping("join")
	public String setJoin(MemberVO memberVO)throws Exception{
		int result = memberService.setJoin(memberVO);
		
		return "redirect:./login";
	}
	
	@GetMapping("login")
	public void getLogin()throws Exception{
		
	}
	
	@PostMapping("login")
	public String getLogin(HttpSession session, MemberVO memberVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		memberVO = memberService.getLogin(memberVO);
		
		session.setAttribute("member", memberVO);
		//mv.addObject("member",memberVO);
		
		return "redirect:../";
	}
	
	@GetMapping("logout")
	public String getLogout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
}

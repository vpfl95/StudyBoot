package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("mypage")
	public void getMyPage()throws Exception{
		
	}
	
	
	
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
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
		
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO, BindingResult bindingResult, ModelAndView mv)throws Exception{
		
//		if(bindingResult.hasErrors()){
//			//검증에 실패하면 회원가입하는 jsp로 forward
//			log.info("===== 검증 에러 발생 =====");
//			mv.setViewName("member/join");
//			return mv;
//		}
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			log.info("===== 검증 에러 발생 =====");
			mv.setViewName("member/join");
			
			//===============================
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError fieldError: errors) {
				log.info("FieldError {}",fieldError);
				log.info("Field {}",fieldError.getField());
				log.info("Message {}", fieldError.getRejectedValue());
				log.info("Default {}",fieldError.getDefaultMessage());
				log.info("code {}",fieldError.getCode());
				mv.addObject(fieldError.getField(),fieldError.getDefaultMessage());
				log.info("========================================");
			}
			
			
			return mv;
		}
		
		
		int result = memberService.setJoin(memberVO);
		mv.setViewName("redirect:./login");
		return mv;
	}
	
	@GetMapping("login")						//파라미터 안넘어오면 기본값 false
	public void getLogin(@RequestParam(defaultValue = "false", required = false) boolean error, String message, Model model)throws Exception{
		//controller에서 받아서 jsp로 다시 보내도 됨
		if(error) {
			model.addAttribute("msg","ID 또는 PW확인");
		}
	}
	
//	@PostMapping("login")
//	public String getLogin(HttpSession session, MemberVO memberVO)throws Exception{
//		ModelAndView mv = new ModelAndView();
//		
//		memberVO = memberService.getLogin(memberVO);
//		
//		session.setAttribute("member", memberVO);
//		//mv.addObject("member",memberVO);
//		
//		return "redirect:../";
//	}
	
//	@GetMapping("logout")
//	public String getLogout(HttpSession session)throws Exception{
//		log.info("======== 내가만든 logout 메서드 ========");
//		session.invalidate();
//		return "redirect:../";
//	}
}

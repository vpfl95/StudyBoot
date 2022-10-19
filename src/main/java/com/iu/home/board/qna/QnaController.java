package com.iu.home.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		pager.setRowNum();
		List<QnaVO> arr = qnaMapper.getList(pager);
		
		mv.addObject("arr",arr);
		mv.setViewName("/board/qna");
		return mv;
	}
	
}

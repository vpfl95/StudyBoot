package com.iu.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {
	
	@GetMapping("/fileDown/{path}")	// RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path, QnaFileVO qnaFileVO)throws Exception{
		log.info("path {}",path);
		ModelAndView mv = new ModelAndView();
		//DB에서 정보 조회
		qnaFileVO.setFileName("19eaa4af-a0d3-4a0f-b8e0-20af01c68e29_ic_content_star_on_20x20_nor_yellow-7ffe4aa6d3c620c559111070c71a20b7.png");
		qnaFileVO.setOriName("ic_content_star_on_20x20_nor_yellow-7ffe4aa6d3c620c559111070c71a20b7.png");
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path",path);
		mv.setViewName("fileManager");
		
		return mv;
	}
	
	
	@GetMapping("/fileDown/notice")
	public ModelAndView fileDownNotice(QnaFileVO qnaFileVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		//DB에서 정보 조회
		qnaFileVO.setFileName("common.jpg");
		qnaFileVO.setOriName("common.jpg");
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path","notice");
		mv.setViewName("fileManager");
		
		return mv;
	}
}

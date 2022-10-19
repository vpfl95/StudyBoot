package com.iu.home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HomeController {
	
	@Value("${my.message.hi}")
	private String message;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private QnaMapper qnaMapper;
	
	@GetMapping("/")
	public String home()throws Exception{
		log.error("Error Message");
		log.warn("Warn Message");
		log.info("Info Message");
		log.debug("Debug Message");
		log.trace("Trace Message");
		
		//List<QnaVO> arr = qnaMapper.getList();
		
		//log.info("List : {} size {}",arr,arr.size());
		
		return "index";
	}
}

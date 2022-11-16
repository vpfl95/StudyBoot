package com.iu.home;



import java.awt.print.Paper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.iu.home.board.qna.PostVO;
import com.iu.home.board.qna.QnaMapper;
import com.iu.home.member.MemberVO;

import reactor.core.publisher.Mono;



@Controller
public class HomeController {
	
	@Value("${my.message.hi}")
	private String message;
	@Value("${my.default}")
	private String app;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manaer Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	@GetMapping("/web")
	public String webClientTest() {
//		WebClient webClient = WebClient.create();
//		webClient = WebClient.create("http://localhost:81");
//		
//		webClient = WebClient.builder()
//							 .baseUrl("")
//							 .defaultHeader("key","value")
//							 .defaultCookie("key","vlaue")
//							 .build();
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("page",1);
//		map.put("kind","title");
//		
//		webClient.get()
//				 .uri("/posts/1",map)
//				 .header("key","value")
//				 .cookie("k","v");
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://jsonplaceholder.typicode.com/")
									   .build();
		 Mono<ResponseEntity<PostVO>> res =webClient.get()
				 									.uri("/posts/1")
				 									.retrieve()
				 									.toEntity(PostVO.class);
		 
		PostVO postVO=res.block().getBody();
		 
		log.info("res => {}",postVO);
		
		return "";
	}
	
	@GetMapping("/address")
	@ResponseBody
	public String address()throws Exception{
		//kakao 로컬 요청
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 294be53c1e23014ac16b87a65ec13c8c");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", "전북 삼성동 100");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json", String.class, req);
		
		return res.getBody();
	}
	
	
	
	@GetMapping("/")
	public String home(HttpSession session)throws Exception{
	
		RestTemplate restTemplate = new RestTemplate();
		
		// 1. Headr
		HttpHeaders headers = new HttpHeaders();
		// headr : key: value
		// headers.add("key","value");
		// headers.set헤더명(:값");
		
		// 2. parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key","value");
		
		// 3.요청 정보 객체(1,2번을 모음)
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		// 4. 전송 후 결과
		ResponseEntity<String> response =restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1",String.class,request);
		String result = response.getBody();
		log.info("response => {}",response);
		
		
		log.info("==========================");
		Enumeration<String> en = session.getAttributeNames();
		
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			log.info("Key => {}",key);
		}
		SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(context!=null) {
			log.info("Context => {}",context );
		}
		
		log.info("message {}",message);
		log.info("default {}",app);
		log.info("==========================");
	
		
		return "index";
	}
}

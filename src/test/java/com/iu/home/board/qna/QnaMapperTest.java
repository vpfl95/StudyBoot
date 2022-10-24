package com.iu.home.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@Rollback(true)
@Transactional
class QnaMapperTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QnaMapper qnaMapper;

	
	@BeforeAll
	static void befoAll() {
		System.out.println("전체 Test 실행전");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("전체 Test 실행후");
	}
	
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Test 메서드 실행전");
		
		
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("Test 메서드 실행후");
	}
	
	//@Test
//	void test2()throws Exception {
//		log.info("test2 case");
//		QnaVO qnaVO = new QnaVO();
//		//for(int i=0; i<1; i++) {
//			qnaVO.setWriter("writer999");
//			qnaVO.setTitle("title999");
//			qnaVO.setContents("contents999");
//			int result = qnaMapper.setAdd(qnaVO);
//		//}
//		assertEquals(1, result);
//	}
//	
//	@Test
//	void test()throws Exception {
//		List<QnaVO> arr = qnaMapper.getList();
//		log.info("List {}",arr);
//		assertNotEquals(0, arr.size());
//	}

}

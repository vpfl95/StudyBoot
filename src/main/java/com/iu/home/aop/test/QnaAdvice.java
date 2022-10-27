package com.iu.home.aop.test;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.iu.home.board.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class QnaAdvice {
	
	@Before("execution(* com.iu.home.board.qna.QnaService.get*(..))")
	public void beforeTest(JoinPoint joinpoint) {
		log.info("-------------before----------------");
		log.info("Args: {}",joinpoint.getArgs());
		log.info("kind: {}",joinpoint.getKind());
		
	}
	
	
	//@Around("execution(* com.iu.home.board.qna.QnaService.set*(..))") 
	public Object aroundTest(ProceedingJoinPoint joinPoint)throws Throwable{	//joinPoint에 set*()이 들어 있음
		log.info("--------before----------");
		//point-cut의 클래스객체
		log.info("Target : {}",joinPoint.getTarget());
		//point-cut의 클래스객체
		log.info("This : {}",joinPoint.getThis());
		//point-cut으로 전달되는 매개변수의 인자값
		log.info("Args : {}",joinPoint.getArgs());
		Object [] objs = joinPoint.getArgs();
		QnaVO qnaVO = (QnaVO)objs[0]; //다형성
		
		Object obj = joinPoint.proceed();
		log.info("--------after----------");
		log.info("Obj : {}", obj);
		
		return obj;
	}
	
}

package com.iu.home.aop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.home.aop.test.Card;
import com.iu.home.aop.test.Transport;
import com.jayway.jsonpath.spi.cache.Cache;

@SpringBootTest
class TransferTest {
	
	@Autowired
	private Transport transport;
	
	@Autowired
	private Card card;
	
	
	@Test
	void test() {
		transport.takeBus();
		transport.takeSubway();
	}

}

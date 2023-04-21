package com.lmio.mlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lmio.mlib.service.RedisService;

@SpringBootTest
class MlibApplicationTests {
	@Autowired
	private RedisService redisService;

	@Test
	void contextLoads() {
		
	}

	@Test
	public void testRedis() {
		String key = "testKey";
		String value = "testValue";
		redisService.setValue(key, value);
		Object result = redisService.getValue(key);
		System.out.println(result);
		assertEquals(value, result);
	}
}

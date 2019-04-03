package com.xqx.frame.config;


import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigTest {
	final static Logger logger = LoggerFactory.getLogger(ConfigTest.class);
	@Test
	public void getStringArrayTest() {
		String[] array = Config.getStringArray("testArray");
		Assert.assertArrayEquals(new String[] {"测","试","数","组"}, array);
	}
}

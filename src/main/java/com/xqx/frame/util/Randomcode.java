package com.xqx.frame.util;

import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class Randomcode extends DefaultKaptcha {

	public Randomcode() {
		Properties properties = new Properties();
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_BORDER, "yes");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, "code");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_IMAGE_WIDTH, "100");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_IMAGE_HEIGHT, "40");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "abcdefghjkmnpqrstwxy23456789");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "35");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_BACKGROUND_CLR_FROM, "white");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
		properties.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "微软雅黑");
		super.setConfig(new Config(properties));
	}
	
	public BufferedImage createImage(HttpServletRequest request) {
		String code = this.createText();
		request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, code);
		return this.createImage(code);
	}
	
	public String getCode(HttpServletRequest request) {
		return (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	}
}

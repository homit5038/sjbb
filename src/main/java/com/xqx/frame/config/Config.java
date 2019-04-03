package com.xqx.frame.config;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author yyhua
 * 
 * @date 2015-04-23
 * 
 * @Description 读取配置
 */
public class Config {

	private static Configuration cfg;

	static {
		try {
			// 读取配置文件，构造配置类
			cfg = new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getString(String key) {
		return cfg.getString(key);
	}

	public static int getInt(String key) {
		return cfg.getInt(key);
	}

	public static String[] getStringArray(String key) {
		return cfg.getStringArray(key);
	}

	/**
	 * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param keyname
	 * @param keyvalue
	 */
	public static void updateProperties(String keyname, String keyvalue) {
		PropertiesConfiguration pcfg = null;
		try {
			pcfg = new PropertiesConfiguration("config.properties");
			// pcfg.setAutoSave(true);
			pcfg.setProperty(keyname, keyvalue);
			pcfg.save();
//			pcfg.save(new File("config.properties"));
			cfg = new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		updateProperties("cs", "cs");
		System.out.println("操作完成");
	}
}

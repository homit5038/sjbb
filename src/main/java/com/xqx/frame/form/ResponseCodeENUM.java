package com.xqx.frame.form;
/**
 * @author Administrator
 */
public interface ResponseCodeENUM {
	/**
	 * 是否成功
	 *
	 * @return isSuccess
	 */
	boolean isSuccess();

	/**
	 * 设置是否成功
	 *
	 * @param success
	 * 		isSuccess
	 */
	void setSuccess(boolean success);

	/**
	 * 执行返回的code
	 *
	 * @return code
	 */
	String getCode();

	/**
	 * 设置执行code
	 *
	 * @param code
	 * 		code
	 */
	void setCode(String code);

	/**
	 * 获取执行message
	 *
	 * @return message
	 */
	String getMessage();

	/**
	 * 设置执行message
	 *
	 * @param message
	 * 		message
	 */
	void setMessage(String message);
}

package com.nip4.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	/**
	 * 处理get请求编码问题
	 * @param attribute
	 * @return
	 */
	public String getParamter(String attribute) {
		String value = request.getParameter(attribute);
		try {
			value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
}

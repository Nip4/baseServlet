package com.nip4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.nip4.utils.EncodingRequest;

public class EncodingFilter implements Filter{

	private FilterConfig config;
	
	//销毁filter
	@Override
	public void destroy() {}

	//过滤操作
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String characterEncoding = config.getInitParameter("CharacterEncoding");
		String contentType = config.getInitParameter("ContentType");
		
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getMethod().equalsIgnoreCase("GET")) {
			//处理get请求编码问题
			request = new EncodingRequest(req);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			//处理post请求编码格式
			request.setCharacterEncoding(characterEncoding);
		}
		//设置响应编码格式
		response.setContentType(contentType+";charset="+characterEncoding);
		chain.doFilter(request, response);
	}

	//初始化filter
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
}

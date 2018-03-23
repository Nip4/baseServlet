package com.nip4.service;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nip4.utils.StringUtils;

public abstract class BaseService extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数表示需要调用的方法
		String methodName = request.getParameter("method");

		if(!StringUtils.isBlank(methodName)) {
			throw new RuntimeException("您要调用的方法"+methodName+",它不存在");
		}
		Method method = null;
		try {
			 method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("请求出错！");
		}
		
		try {
			String result = (String) method.invoke(this, request,response);
			if(!StringUtils.isBlank(result)) {
				return;
			}
			
			//判断返回的类型中是否存在':',没有存在就表示转发
			if(result.contains(":")) {
				//返回冒号存在的位置
				int index = result.indexOf(":");
				//返回冒号前的字符
				String operate  = result.substring(0,index);
				//返回冒号后的请求路径
				String path = result.substring(index+1);
				
				//如果前缀为r表示重定向，如果是f表示转发
				//equalsIgnoreCase:用于比较2个字符串的内容，在执行时会忽略2个字符串的大小写
				if(operate.equalsIgnoreCase("r")) {
					//重定向到指定的位置
					response.sendRedirect(request.getContextPath()+path);
				}else if(operate.equalsIgnoreCase("f")) {
					request.getRequestDispatcher(path).forward(request, response);
				}else {
					throw new RuntimeException("您指定的操作"+operate+"出错");
				}
			}else {
				//在没有冒号存在时默认作转发处理
				request.getRequestDispatcher("result").forward(request, response);
			}
		
		} catch (Exception e) {
			throw new RuntimeException("您要调用的方法:"+methodName+",内部抛出了异常");
		}
	}
}

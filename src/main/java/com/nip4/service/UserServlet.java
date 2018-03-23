package com.nip4.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nip4.utils.StringUtils;

/**
 * @author Nip4
 *
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseService{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用于用户登录功能
	 * @param request
	 * @param response
	 * @return path
	 */
	public String login(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + "  " + password );
		if(StringUtils.isBlank(username)&&username.equals("chengshuang")&&password.equals("5201314")) {
			System.out.println("登录成功！");
			return "f:/LoginFailed.jsp";
		}
		return "f:/Login.jsp";
	}
	
	/**
	 * 用于用户注册功能
	 * @param request
	 * @param response
	 * @return path
	 */
	public String register(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return null;
	}
}

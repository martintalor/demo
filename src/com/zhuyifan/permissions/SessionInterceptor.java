package com.zhuyifan.permissions;

 
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.zhuyifan.permissions.CommonConstants.LOGIN_INFO.*;

/**
 * @desc: 全局拦截器功能
 * @author: haohu
 * @createTime: 2017-04-21
 * @version: 1.0
 */
public class SessionInterceptor implements HandlerInterceptor {

	private static PathMatcher matcher = new AntPathMatcher();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			return true;
		}
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		final Class<?> clazz = method.getDeclaringClass();
		if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
			if (request.getSession().getAttribute(SESSION_KEY) == null) {
				//throw new Exception();
				response.setHeader(SESSION_HEADER_KEY, INVALIDA_SESSION_STATUS);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().print(RESPONSE_BODY_CONTENT);
				return false;
			}
		}
		return true;
	}


}
package xin.yanhaohai.myweb.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//设置拦截器
public class LoginInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查session中是否有用户对象，如果有则放行，如果没有则重定向到登录界面
        Object user = request.getSession().getAttribute("user");
        if(user == null){

            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        }else {
            return true;
        }
    }
}

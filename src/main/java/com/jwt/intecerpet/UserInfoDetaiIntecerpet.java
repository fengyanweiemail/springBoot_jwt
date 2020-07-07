package com.jwt.intecerpet;

import com.jwt.annotion.PassToken;
import com.jwt.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by fyw on 2020/7/6.
 */
public class UserInfoDetaiIntecerpet implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //不是映射到方法，直接返回
       if( !(handler instanceof HandlerMethod)){
           return true;
       }
        Method method = ((HandlerMethod) handler).getMethod();
        System.out.println(method.getName());
        System.out.println(method.getAnnotation(PassToken.class));
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken annotation = method.getAnnotation(PassToken.class);
            if(annotation.require()){
                return true;
            }
        }
        //获取用户信息
        String token = request.getParameter("token");
        if(token==null||"".equals(token)){
            writerErrorMsg(response);
            return false;
        }
        //验证token
        //获取登录信息
        //保存上下文登录信息
        Boolean b = JwtUtil.verifyToken(token);
        if(!b){
            writerErrorMsg(response);
            return false;
        }
        //token校验通过
        return true;
    }

    /**
     * 利用response直接输出错误信息
     * @param response
     * @throws IOException
     */
    private void writerErrorMsg (HttpServletResponse response){
        try{
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("{\"code\":\"0\",\"businessCode\":\"401\",\"msg\":\"无效的token，请重新登录\",\"data\":\"\"}");

        }catch (Exception e){

        }finally {
            try {
                response.getWriter().close();
            }catch (Exception e){

            }

        }
    }
}

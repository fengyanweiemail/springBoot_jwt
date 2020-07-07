package com.jwt.controller;

import com.jwt.annotion.PassToken;
import com.jwt.model.UserDetail;
import com.jwt.util.JwtUtil;
import com.jwt.util.UserContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fyw on 2020/7/7.
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @PassToken
    @GetMapping("/getUser/{id}")
    public Map<String,Object> getUser(@PathVariable("id") String id){
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","登录成功");
        UserDetail userDetail = new UserDetail();
        userDetail.setId(id);
        map.put("token",JwtUtil.getSignToken(userDetail));
        return map;
    }


    @GetMapping("/testLogin")
    public Map<String,Object> testLogin(){
        UserDetail userDetail = UserContextUtil.getUserDetail();

        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","登录成功");
        map.put("登录id",userDetail.getId());
        return map;
    }
}

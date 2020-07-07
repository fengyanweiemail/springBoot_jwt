package com.jwt.util;

import com.jwt.model.UserDetail;

/**
 * Created by fyw on 2020/7/6.
 */
public class UserContextUtil {
    private static final  ThreadLocal<UserDetail> USER_DETAIL = new ThreadLocal<>();


    public static void setUserDetail(UserDetail userDetail){
        USER_DETAIL.set(userDetail);
    }

    public static  UserDetail getUserDetail(){
        return USER_DETAIL.get();
    }

}

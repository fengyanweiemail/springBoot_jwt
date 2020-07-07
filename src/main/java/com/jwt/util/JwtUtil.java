package com.jwt.util;

import com.jwt.model.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fyw on 2020/7/7.
 */
public class JwtUtil {

    private static String SECRET = "abcdefg@$dgdhbfgbtt%123$$$dfd";

    /**
     * 获取加密的token信息
     * @param userDetail
     * @return
     */
    public static String getSignToken(UserDetail userDetail){
        Map<String,Object > userMap = new HashMap<>();
        userMap.put("id",userDetail.getId());
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + 1800L * 1000);
        String token = Jwts.builder().setHeaderParam("typ","JWT")
                .setClaims(userMap)
                .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS256,SECRET)
        .compact();
        return token;
    }


    /**
     * 校验token正确性和是否失效
     * @param token
     * @return
     */
    public static Boolean verifyToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            if(claims==null){
                return false;
            }else{
                UserDetail userDetail = new UserDetail();
                userDetail.setId(claims.get("id").toString());
                UserContextUtil.setUserDetail(userDetail);
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }



}

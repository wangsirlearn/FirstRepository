package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {
    /**
     * 生成JWT令牌 Jwts.Builder()
     */

    @Test
    public void GenerateJWTToken() {

        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","wys");
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "d3lz")// 生成JWT令牌并指定签名算法和密钥 密匙base64编码
                .addClaims(dataMap)//添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))//设置令牌过期时间
                .compact();//生成令牌
        System.out.println(token);


    }
    /**
     * 解析令牌 Jwts.parser()
     */
    @Test
    public void parseToken() throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey("d3lz")//指定密钥 必须和生成令牌时使用的密钥一致
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ3eXMiLCJleHAiOjE3NzE0MjU5NDR9.KqJ3izmH8u9eJbUsWRHa3vYfUv1XdrjFVzGLAc95bJw")
                .getBody();//获取令牌中的自定义数据
        System.out.println( claims); //获取数据
    }
}

package com.example.commonutils.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * create by Freedom on 2020/9/25
 */
public class JwtUtils {
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static String getJwtToken(String id, String nickname){

        return Jwts.builder()
                //JWT头部分是一个描述JWT元数据的JSON对象
                .setHeaderParam("typ", "JWT") //typ属性表示令牌的类型，JWT令牌统一写为JWT。
                .setHeaderParam("alg", "HS256")//alg属性表示签名使用的算法，默认为HMAC SHA256（写为HS256）

                //有效载荷是JWT的主体内容部分，也是一个JSON对象，包含需要传递的数据。
                /*
                iss：发行人
                exp：到期时间
                sub：主题
                aud：用户
                nbf：在此之前不可用
                iat：发布时间
                jti：JWT ID用于标识该JWT
                除以上默认字段外，我们还可以自定义私有字段，如下例：
                 */
                .setSubject("guli-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nickname", nickname)
                /*
                签名哈希
                签名哈希部分是对上面两部分数据签名，通过指定的算法生成哈希，以确保数据不会被篡改。
                首先，需要指定一个密码（secret）。该密码仅仅为保存在服务器中，并且不能向用户公开。
                然后，使用标头中指定的签名算法（默认情况下为HMAC SHA256）根据以下公式生成签名。
                 */
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken jwtToken
     * @return boolean
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request request
     * @return boolean
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request request
     * @return id
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}

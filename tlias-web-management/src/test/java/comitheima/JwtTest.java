package comitheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /*
    生成jwt令牌
     */
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")//创建jwt令牌,signWith指定签名的算法,itheima所转的base64编码字符串作为密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//设置令牌过期时间
                .compact();//compact生成令牌
        System.out.println(jwt);

    }

    /*
    解析jwt令牌
     */
    @Test
    public void testParseJwt() {
       Claims claims= Jwts.parser().setSigningKey("aXRoZWltYQ==")//设置密钥(与上面生成的要一样）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3OTM1NDA4Nn0.j1M4nxsw8AtUQe2W6PAg3cbb_Lk_hLQfYnJiRx7iRLU")
                .getBody();
        System.out.println( claims);
    }
}

package xyz.veiasai.controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.veiasai.jwt.TokenJWT;
import xyz.veiasai.pojo.JWT;
import xyz.veiasai.pojo.Result;
import xyz.veiasai.pojo.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@EnableAutoConfiguration
public class Main {
    @RequestMapping("/gettoken")
    @ResponseBody
    JWT CreateToken(@RequestBody User user) {
        String id = user.getUserId();
        JWT jwt = new JWT();
        if (id != null && id.equals("Veia")) {
            String jwtString = TokenJWT.createJWT("test", TokenJWT.generalSubject(user.getUserId()), 60000);
            jwt.setJwt(jwtString);
            jwt.setCode(200);
        }
        else {
            jwt.setCode(400);
        }
        return jwt;
    }

    @RequestMapping("/testtoken")
    @ResponseBody
    Result ParseToken(@RequestBody JWT jwt) {
        Result result = new Result();
        try {
            Claims claim = TokenJWT.parseJWT(jwt.getJwt());
            result.setBody(claim.getSubject());
            result.setCode(200);
        } catch (Exception e) {
            result.setBody(e.toString());
            result.setCode(400);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}

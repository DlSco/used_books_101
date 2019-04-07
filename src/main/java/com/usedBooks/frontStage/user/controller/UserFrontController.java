package com.usedBooks.frontStage.user.controller;

import com.usedBooks.frontStage.user.service.UserFrontService;
import com.usedBooks.pojo.User;
import com.usedBooks.redis.RedisService;
import com.usedBooks.redis.UserKey;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserFrontController {

    @Autowired
    private UserFrontService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 登录
     * @param user
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public Result login(User user, HttpServletResponse response){
        return Result.success(userService.login(response,user));
    }


    /**
     * 注册
     */
    @RequestMapping("/register")
    public Result register(User user){
        if(userService.register(user)>0){
            return Result.success(null);
        }
        return null;
    }


    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public Result<Boolean> logout(HttpServletRequest request, HttpServletResponse response){
        log.info("申请退出！");

        Cookie[] cookies = request.getCookies();

        String tk = null;
        log.info("Cookies:"+cookies);
        for (Cookie cookie: cookies) {
            log.info("cookiename={}, cookievalue={}", cookie.getName(), cookie.getValue());
            if(cookie.getName().equals("userToken")){
                tk = cookie.getValue();
                log.info("token--->"+tk);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        if(tk == null){
            Result.error(CodeMsg.SERVER_ERROR);
        }

        redisService.delete(UserKey.token, tk);

        return Result.success(true);

    }


    /**
     * 获取登录信息
     * @param token
     * @param response
     * @return
     */
    @RequestMapping("/getUser")
    public Result getAdmin(String token,HttpServletResponse response){
        return Result.success(userService.getByToken(response,token));
    }

}

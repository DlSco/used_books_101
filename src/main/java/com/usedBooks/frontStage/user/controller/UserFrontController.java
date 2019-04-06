package com.usedBooks.frontStage.user.controller;

import com.usedBooks.frontStage.user.service.UserFrontService;
import com.usedBooks.pojo.User;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserFrontController {

    @Autowired
    private UserFrontService userService;

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
}

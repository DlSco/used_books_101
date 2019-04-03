package com.usedBooks.manager.loginModule.controller;

import com.usedBooks.manager.loginModule.pojo.Admin;
import com.usedBooks.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Manager/loginModule")
public class ManagerLoginController {

    @RequestMapping("/doLogin")
    public Result doLogin(HttpServletResponse response, Admin admin){
        return null;
    }
}

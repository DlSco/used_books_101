package com.usedBooks.manager.loginModule.service;

import com.usedBooks.manager.loginModule.pojo.Admin;

import javax.servlet.http.HttpServletResponse;

public interface AdminService {

    //登录验证
    public String login(HttpServletResponse response, Admin admin);
    public Admin getByToken(HttpServletResponse response, String token);
}

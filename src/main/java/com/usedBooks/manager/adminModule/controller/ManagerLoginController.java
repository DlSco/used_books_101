package com.usedBooks.manager.adminModule.controller;

import com.usedBooks.manager.adminModule.pojo.Admin;
import com.usedBooks.manager.adminModule.service.AdminService;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Manager/adminModule")
public class ManagerLoginController {


    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param response
     * @param admin
     * @return
     */
    @RequestMapping("/doLogin")
    public Result doLogin(HttpServletResponse response, Admin admin){
        return Result.success(adminService.login(response,admin));
    }

    /**
     * 获取登录信息
     * @param token
     * @param response
     * @return
     */
    @RequestMapping("/getAdmin")
    public Result getAdmin(String token,HttpServletResponse response){
        return Result.success(adminService.getByToken(response,token));
    }
}

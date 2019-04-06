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
    @RequestMapping("/doLogin")
    public Result doLogin(HttpServletResponse response, Admin admin){
        return Result.success(adminService.login(response,admin));
    }
}

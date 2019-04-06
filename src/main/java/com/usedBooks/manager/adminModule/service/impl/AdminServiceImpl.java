package com.usedBooks.manager.adminModule.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.usedBooks.exception.GlobalException;
import com.usedBooks.manager.adminModule.mapper.AdminMapper;
import com.usedBooks.manager.adminModule.pojo.Admin;
import com.usedBooks.manager.adminModule.service.AdminService;
import com.usedBooks.redis.AdminKey;
import com.usedBooks.redis.RedisService;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.util.MD5Util;
import com.usedBooks.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private  final Logger logger= LoggerFactory.getLogger(AdminServiceImpl.class) ;


    public static final String COOKI_NAME_TOKEN = "tokenAdmin";
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    RedisService redisService;

    public Admin getByUserName(String userName) {
        //取缓存
        Admin admin = redisService.get(AdminKey.getByUserName, ""+userName, Admin.class);
        if(admin != null) {
            return admin;
        }
        //取数据库
        admin = new Admin();
        admin.setAdminName(userName);
        admin = (Admin) adminMapper.select(admin);

        if(admin != null) {
            redisService.set(AdminKey.getByUserName, ""+userName, admin);
        }
        return admin;
    }



    @Override
    public Admin getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        Admin admin = redisService.get(AdminKey.token, token, Admin.class);
        //延长有效期
        if(admin != null) {
            addCookie(response, token, admin);
        }
        return admin;
    }


    @Override
    public String login(HttpServletResponse response, Admin admin) {
        if(admin == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String userName = admin.getAdminName();
        String formPass = admin.getPassword();
        //判断手机号是否存在
        Admin tempAdmin = getByUserName(userName);
        if(tempAdmin == null) {
            throw new GlobalException(CodeMsg.ADMIN_NOT_EXIST);
        }
        //验证密码
        String dbPass = tempAdmin.getPassword();
        String calcPass = MD5Util.formPassToDBPass(formPass, MD5Util.getSalt());
        logger.info("calcPass:"+calcPass);
        logger.info("formPass:"+formPass+"  dbPass:"+dbPass);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token= UUIDUtils.getUUID();
        addCookie(response, token, tempAdmin);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, Admin admin) {
        redisService.set(AdminKey.token, token, admin);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(AdminKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

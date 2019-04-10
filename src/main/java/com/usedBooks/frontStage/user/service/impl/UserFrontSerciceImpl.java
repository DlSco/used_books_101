package com.usedBooks.frontStage.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.usedBooks.exception.GlobalException;
import com.usedBooks.frontStage.user.mapper.UserFrontMapper;
import com.usedBooks.frontStage.user.service.UserFrontService;
import com.usedBooks.pojo.User;
import com.usedBooks.redis.AdminKey;
import com.usedBooks.redis.RedisService;
import com.usedBooks.redis.UserKey;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.util.MD5Util;
import com.usedBooks.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFrontSerciceImpl implements UserFrontService {

    public static final String TOKEN = "userToken";
    @Autowired
    private UserFrontMapper userMapper;

    @Autowired
    RedisService redisService;

    public User getByTelePhone(String phone) {
        //取缓存
        User user = redisService.get(UserKey.getByUserName, ""+phone, User.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = new User();
        user.setPhone(phone);
        List<User> list = userMapper.select(user);
        user = list.size()==0?null:list.get(0);

        if(user != null) {
            redisService.set(UserKey.getByPhone, ""+phone, user);
        }
        return user;
    }



    @Override
    public User getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }


    @Override
    public String login(HttpServletResponse response, User user) {
        if(user == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String phone = user.getPhone();
        String formPass = user.getPassword();
        //判断手机号是否存在
        User tempUser = getByTelePhone(phone);
        if(tempUser == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        if(tempUser.getIsDelete()==1){
            throw new GlobalException(CodeMsg.USER_COLD_ERROR);
        }
        //验证密码
        String dbPass = tempUser.getPassword();
        String calcPass = MD5Util.formPassToDBPass(formPass, MD5Util.getSalt());
        log.info("calcPass:"+calcPass);
        log.info("formPass:"+formPass+"  dbPass:"+dbPass);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token= UUIDUtils.getUUID();
        addCookie(response, token, tempUser);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    /**
     * 注册
     */
    public int register(User user){

        User tempUser  = new User();
        tempUser.setUserName(user.getUserName());
        if(userMapper.selectCount(tempUser)>0){
            throw new GlobalException(new CodeMsg(5001400,"用户名已存在"));
        }
        tempUser = new User();
        tempUser.setPhone(user.getPhone());
        if(userMapper.selectCount(tempUser)>0){
            throw new GlobalException(CodeMsg.MOBILE_EXITS_REGISTER);
        }

        String calcPass = MD5Util.formPassToDBPass(user.getPassword(), MD5Util.getSalt());
        user.setPassword(calcPass);

        return userMapper.insert(user);

    }
}

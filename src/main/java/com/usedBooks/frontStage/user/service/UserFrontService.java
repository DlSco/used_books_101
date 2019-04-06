package com.usedBooks.frontStage.user.service;


import com.usedBooks.pojo.User;

import javax.servlet.http.HttpServletResponse;

public interface UserFrontService {

    User getByToken(HttpServletResponse response, String token);
    String login(HttpServletResponse response, User user);
    int register(User user);
}

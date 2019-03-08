package com.usedBooks.manager.userModule.service;

import com.usedBooks.pojo.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserService {
    long countByUser(User user);

    User getByPrimaryKey(String id);

    List<User> listWithRowbounds(User user, RowBounds rowBounds);

    int removeByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    int removeByUser(User user);

    int save(User user);

    int saveSelective(User user);
}
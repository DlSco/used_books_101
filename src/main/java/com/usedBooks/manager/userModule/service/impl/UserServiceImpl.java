package com.usedBooks.manager.userModule.service.impl;

import com.usedBooks.manager.userModule.service.UserService;
import com.usedBooks.mapper.UserMapper;
import com.usedBooks.pojo.User;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public User getByPrimaryKey(String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public long countByUser(User user) {
        long count = this.userMapper.countByUser(user);
        logger.debug("count: {}", count);
        return count;
    }

    public List<User> listWithRowbounds(User user, RowBounds rowBounds) {
        return this.userMapper.selectWithRowbounds(user,rowBounds);
    }

    public int removeByPrimaryKey(String id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    public int updateByPrimaryKey(User user) {
        return this.userMapper.updateByPrimaryKey(user);
    }

    public int removeByUser(User user) {
        return this.userMapper.deleteByUser(user);
    }

    public int save(User user) {
        return this.userMapper.insert(user);
    }

    public int saveSelective(User user) {
        return this.userMapper.insertSelective(user);
    }
}
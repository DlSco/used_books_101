package com.usedBooks.manager.userModule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.manager.userModule.service.UserService;
import com.usedBooks.mapper.UserMapper;
import com.usedBooks.pojo.User;
import com.usedBooks.util.MyBeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public User getByPrimaryKey(Integer id) {
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

    public int removeByPrimaryKey(Integer id) {
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

    public PageInfo toList(User user,Integer page,Integer limit,String key, String value){
        Map<String,Object> map =MyBeanUtils.beanToMap(user);
        map.put("key",key);
        map.put("value",value);
        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        List<User> list = userMapper.toList(map);
        return new PageInfo(list);
    }

}
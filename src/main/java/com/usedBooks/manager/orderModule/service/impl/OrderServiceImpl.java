package com.usedBooks.manager.orderModule.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.mapper.OrderDetailMapper;
import com.usedBooks.mapper.OrderMapper;
import com.usedBooks.manager.orderModule.service.OrderService;
import com.usedBooks.pojo.Order;
import com.usedBooks.pojo.OrderDetail;
import com.usedBooks.util.MyBeanUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public Order getByPrimaryKey(Integer id) {
        return this.orderMapper.selectByPrimaryKey(id);
    }

    public long countByOrder(Order order) {
        long count = this.orderMapper.countByOrder(order);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Order> listWithRowbounds(Order order, RowBounds rowBounds) {
        return this.orderMapper.selectWithRowbounds(order,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.orderMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Order order) {
        return this.orderMapper.updateByPrimaryKeySelective(order);
    }

    public int updateByPrimaryKey(Order order) {
        return this.orderMapper.updateByPrimaryKey(order);
    }

    public int removeByOrder(Order order) {
        return this.orderMapper.deleteByOrder(order);
    }

    public int save(Order order) {
        return this.orderMapper.insert(order);
    }

    public int saveSelective(Order order) {
        return this.orderMapper.insertSelective(order);
    }

    @Override
    public PageInfo<Order> getList(Integer page, Integer limit, Order order, String key, String value) {

        Map<String,Object> map = MyBeanUtils.beanToMap(order);
        List<Order> list = null;
        map.put("key",key);
        map.put("value",value);
        //分页
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }
        list = orderMapper.getList(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }




}
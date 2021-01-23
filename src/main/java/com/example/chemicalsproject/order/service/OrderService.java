package com.example.chemicalsproject.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.order.mapper.OrderMapper;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 根据条件查询 联合查询
     * @param page
     * @param condition
     * @return
     */
    public PageInfo<Order> queryAll(Integer page, Integer limit, OrderListConditionUtil condition) {
        System.out.println("service queryAll employee======" + condition);
        PageHelper.startPage(page, limit);
        List<Order> orderList = orderMapper.queryAll(condition);
        PageInfo<Order> info=new PageInfo<>(orderList);
        return info;
    }

    /**
     * 根据id查询一条employee
     * @param id
     * @return
     */
    public Order queryById(Integer id) {
        return orderMapper.queryById(id);
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    public boolean delOrder(String id){
        return orderMapper.delOrder(id);
    }

    /**
     * 设置固定格式的编号  订单编号DD00482
     * @param id
     * @return
     */
    public String selfFormatId(Integer id){
        return String.format("DD%05d", id);
    }

    /**
     * 根据did修改订单库存状态
     * @param status
     * @param did
     * @return
     */
    public boolean updateStatus(String status,Integer did){
        return orderMapper.updateStatus(status,did);
    }

    /**
     * 添加订单
     * @param order
     * @return
     */
    public boolean saveOrder(Order order){
        return orderMapper.saveOrder(order);
    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    public boolean updateOrder(Order order){
        return orderMapper.updateOrder(order);
    }

    public static void main(String[] args) {
//        System.out.println(String.format("AH%05d", 50));
        List<Integer> ll = new ArrayList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);

        Integer findValue=2;

        if(ll.contains(findValue))
            System.out.println("existed: " + findValue);
        else System.out.println("not existed: " + findValue);
    }

    /**
     * 根据状态查询订单列表
     * @param status
     * @return
     */
    public PageInfo<Order> queryOrderByStatus(Integer page,Integer limit,Integer status){
        PageHelper.startPage(page, limit);
        List<Order> orderList = orderMapper.queryOrderByStatus(status);
        PageInfo<Order> info=new PageInfo<>(orderList);
        return info;
    }

}

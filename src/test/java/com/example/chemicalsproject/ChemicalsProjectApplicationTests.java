package com.example.chemicalsproject;

import com.example.chemicalsproject.order.service.OrderService;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Order;
import com.example.chemicalsproject.pojo.Sales;
import com.example.chemicalsproject.pojo.User;
import com.example.chemicalsproject.login.service.LoginService;
import com.example.chemicalsproject.sales.service.SalesService;
import com.example.chemicalsproject.sales.util.SalesListConditionUtil;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class ChemicalsProjectApplicationTests {

    @Autowired
    private LoginService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SalesService salesService;



    @Test
    void contextLoads() {
        /*List<User> list = userService.list();
        List<String> names=null;
        for (User user:list){
            System.out.println(user.getUsername());
        }*/
//        System.out.println(orderService.queryById(1));
        /*OrderListConditionUtil orderListConditionUtil = new OrderListConditionUtil();
        orderListConditionUtil.setUser_rule(1);
        orderListConditionUtil.setUser_id(2);
        PageInfo<Order> orderPageInfo = orderService.queryAll(1, 20, orderListConditionUtil);
        System.out.println(orderPageInfo.getList());*/
//        orderService.updateStatus("1",486);
//        System.out.println(salesService.queryById(46));
        SalesListConditionUtil condition=new SalesListConditionUtil();
        condition.setPage(1);
        condition.setLimit(20);
        condition.setUser_rule(2);
        condition.setUser_id(2);
        PageInfo<Sales> salesPageInfo = salesService.queryAll(condition);
        salesPageInfo.getList().forEach(System.out::println);
//        System.out.println(orderService.queryOrderByStatus(1, 20, 2).getList().size());
        /*Sales sales=new Sales();
        sales.setUser_id(1);
        sales.setOrder_id(497);
        sales.setTracking_name("111");
        sales.setTracking_number("111");
        sales.setStatus(0);
        sales.setCreate_time(new Date(new java.util.Date().getTime()));
        System.out.println(salesService.save(sales));*/
    }

}

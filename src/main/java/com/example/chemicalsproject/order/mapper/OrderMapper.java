package com.example.chemicalsproject.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询列表
     * @param condition
     * @return
     */
    List<Order> queryAll(OrderListConditionUtil condition);

    /**
     * 根据id查询订单
     * @param did
     * @return
     */
    Order queryById(@Param("did")Integer did);

    /**
     * 删除订单
     * @param did
     * @return
     */
    @Delete("delete from `order` where did=#{value}")
    boolean delOrder(String did);

    /**
     * 根据did修改订单库存状态
     * @param status
     * @param did
     * @return
     */
    @Update("update `order` set `status`=#{status} where did=#{did}")
    boolean updateStatus(String status,Integer did);

    /**
     * 添加订单
     * @param order
     * @return
     */
    /*@Insert("insert into `order` value (null,#{user_id},#{amount},#{price},#{cost_price},#{royalties}," +
            "#{other_cost},#{bill},#{bill_info},#{commodity_id},#{invoice_id},0,#{user_name}," +
            "#{address},#{phone},#{create_time},#{remarks})")*/
    boolean saveOrder(Order order);

    /**
     * 修改订单
     * @param order
     * @return
     */
    boolean updateOrder(Order order);

    /**
     * 根据状态查询订单列表
     * @param status
     * @return
     */
    List<Order> queryOrderByStatus(@Param("status") Integer status);

}

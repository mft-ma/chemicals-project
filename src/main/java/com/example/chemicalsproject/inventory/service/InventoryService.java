package com.example.chemicalsproject.inventory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.inventory.mapper.InventoryMapper;
import com.example.chemicalsproject.inventory.util.InventoryListConditionUtil;
import com.example.chemicalsproject.order.mapper.OrderMapper;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Inventory;
import com.example.chemicalsproject.pojo.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InventoryService extends ServiceImpl<InventoryMapper, Inventory> {
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 根据条件查询 联合查询
     * @param page
     * @param condition
     * @return
     */
    public PageInfo<Inventory> queryAll(Integer page, Integer limit, InventoryListConditionUtil condition) {
        System.out.println("service queryAll employee======" + condition);
        PageHelper.startPage(page, limit);
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
        if(condition.getCommodityName()!=null && !"".equals(condition.getCommodityName())){
            queryWrapper.like("name",condition.getCommodityName());
        }
        if(condition.getInventory_cas()!=null && !"".equals(condition.getInventory_cas())){
            queryWrapper.eq("cas",condition.getInventory_cas());
        }
        if(condition.getInventoryNumber()!=null && !"".equals(condition.getInventoryNumber())){
            queryWrapper.eq("number",condition.getInventoryNumber());
        }
        List<Inventory> inventories = inventoryMapper.selectList(queryWrapper);
        System.out.println("service queryAll inventories======");
        inventories.forEach(System.out::println);
        PageInfo<Inventory> info=new PageInfo<>(inventories);
        return info;
    }

    /**
     * 根据条件查询 联合查询0库存数据
     * @param page
     * @param condition
     * @return
     */
    public PageInfo<Inventory> queryAmountList(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
        queryWrapper.le("amount",0);
        List<Inventory> inventories = inventoryMapper.selectList(queryWrapper);
        System.out.println("service queryAll inventories======");
        inventories.forEach(System.out::println);
        PageInfo<Inventory> info=new PageInfo<>(inventories);
        return info;
    }

    /**
     * 增加库存数量
     * @param amount
     * @param kid
     * @return
     */
    public boolean updAddAmount(Integer amount,Integer kid){
        return inventoryMapper.updAddAmount(amount,kid);
    }

    /**
     * 减少库存数量
     * @param amount
     * @param kid
     * @return
     */
    public boolean updReduceAmount(Integer amount,Integer kid){
        return inventoryMapper.updReduceAmount(amount,kid);
    }

}

package com.example.chemicalsproject.sales.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Order;
import com.example.chemicalsproject.pojo.Sales;
import com.example.chemicalsproject.sales.mapper.SalesMapper;
import com.example.chemicalsproject.sales.util.SalesListConditionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalesService extends ServiceImpl<SalesMapper, Sales> {

    @Autowired
    private SalesMapper salesMapper;

    /**
     * 根据id查询退货信息
     * @param id
     * @return
     */
    public Sales queryById(Integer id){
        return salesMapper.queryById(id);
    }

    /**
     * 根据商品名称或物流编号查询 联合查询
     * @param salesUtil
     * @return
     */
    public PageInfo<Sales> queryAll(SalesListConditionUtil salesUtil) {
        System.out.println("service queryAll employee======" + salesUtil);
        PageHelper.startPage(salesUtil.getPage(), salesUtil.getLimit());
        List<Sales> salesList = salesMapper.queryAll(salesUtil);
        PageInfo<Sales> info=new PageInfo<>(salesList);
        return info;
    }

    /**
     * 设置固定格式的编号  退货编号TH00001
     * @param id
     * @return
     */
    public String selfFormatId(Integer id){
        return String.format("TH%05d", id);
    }

    public static void main(String[] args) {
        SalesService salesService=new SalesService();
        SalesListConditionUtil condition=new SalesListConditionUtil();
        condition.setPage(1);
        condition.setLimit(5);
        condition.setCommodityName("氧气");
        PageInfo<Sales> salesPageInfo = salesService.queryAll(condition);
        salesPageInfo.getList().forEach(System.out::println);
    }
}

package com.example.chemicalsproject.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Inventory;
import com.example.chemicalsproject.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    @Update("update inventory set amount=amount+#{amount} where kid=#{kid}")
    boolean updAddAmount(Integer amount,Integer kid);

    @Update("update inventory set amount=amount-#{amount} where kid=#{kid}")
    boolean updReduceAmount(Integer amount,Integer kid);
}

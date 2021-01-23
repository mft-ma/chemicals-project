package com.example.chemicalsproject.sales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.Sales;
import com.example.chemicalsproject.sales.util.SalesListConditionUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalesMapper extends BaseMapper<Sales> {

    /**
     * 根据id查询退货记录
     * @param did
     * @return
     */
    Sales queryById(@Param("did") Integer did);

    /**
     * 根据商品名称或物流编号查询退货list
     * @param salesListConditionUtil
     * @return
     */
    List<Sales> queryAll(SalesListConditionUtil salesListConditionUtil);
}

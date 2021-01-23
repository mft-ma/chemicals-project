package com.example.chemicalsproject.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommodityOrderMapper extends BaseMapper<Commodity> {
}

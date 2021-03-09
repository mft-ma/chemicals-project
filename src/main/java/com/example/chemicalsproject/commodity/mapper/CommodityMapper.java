package com.example.chemicalsproject.commodity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.commodity.util.CommodityDo;
import com.example.chemicalsproject.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
    public List queryCommodity(CommodityDo commodityDo);


    @Select("select * from commodity left join users on user_id=uid where sid=#{value}")
    public Commodity queryCommodityById(Integer sid);

    @Select("select i.amount from commodity c,inventory i where c.cas=i.cas and c.sid=#{sid}")
    Double queryCommodityAmountById(Integer sid);
}

package com.example.chemicalsproject.commodity.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.commodity.mapper.CommodityMapper;
import com.example.chemicalsproject.commodity.util.CommodityDo;
import com.example.chemicalsproject.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
    @Autowired
    private CommodityMapper commodityMapper;

    public List queryCommodity (CommodityDo commodityDo){
        return commodityMapper.queryCommodity(commodityDo);
    }

    public Commodity queryCommodityById(Integer sid){
        return commodityMapper.queryCommodityById(sid);
    }
}

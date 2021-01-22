package com.example.chemicalsproject.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.order.mapper.CommodityMapper;
import com.example.chemicalsproject.pojo.Commodity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {
}

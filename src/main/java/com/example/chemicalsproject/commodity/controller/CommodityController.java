package com.example.chemicalsproject.commodity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chemicalsproject.commodity.service.CommodityService;
import com.example.chemicalsproject.commodity.util.CommodityDo;
import com.example.chemicalsproject.pojo.Commodity;
import com.example.chemicalsproject.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/queryCommodity")
    public List queryCommodity(CommodityDo commodityDo){
        return commodityService.queryCommodity(commodityDo);
    }

    @RequestMapping("/queryCommodityBySid")
    @ResponseBody
    public Commodity queryCommodityById(Integer sid){
       return commodityService.queryCommodityById(sid);
    }

    @RequestMapping("/delCommodity")
    @ResponseBody
    public boolean delCommodity(Integer sid){
        return commodityService.removeById(sid);
    }

    @RequestMapping("/saveCommodity")
    @ResponseBody
    public boolean saveCommodity(@RequestBody Commodity commodity){
        commodity.setCreateTime(new Date(new java.util.Date().getTime()));//创建时间
        if(commodity.getSupplierId()!=null){
            String status="已关联";
            Integer gid=commodity.getSupplierId();
            supplierService.supplierUpd(status,gid);
        }
        return commodityService.save(commodity);
    }

    @RequestMapping("/updCommodity")
    @ResponseBody
    public boolean updCommodity(@RequestBody Commodity commodity){
        return commodityService.updateById(commodity);
    }

}

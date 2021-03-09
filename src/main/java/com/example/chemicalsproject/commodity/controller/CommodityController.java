package com.example.chemicalsproject.commodity.controller;

import com.example.chemicalsproject.commodity.service.CommodityService;
import com.example.chemicalsproject.commodity.util.CommodityDo;
import com.example.chemicalsproject.pojo.Commodity;
import com.example.chemicalsproject.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
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
         List<Commodity> list=commodityService.queryCommodity(commodityDo);
         for (Commodity commodity:list){
             commodity.setFormatId(commodityService.IdFormat(commodity.getSid()));
         }
         return list;
    }

    @RequestMapping("/queryCommodityBySid")
    @ResponseBody
    public Commodity queryCommodityById(Integer sid){
       return commodityService.queryCommodityById(sid);
    }

    /**
     * 根据商品id查询库存数量
     * @param sid
     * @return
     */
    @RequestMapping("/queryCommodityAmountById")
    @ResponseBody
    public Double queryCommodityAmountById(Integer sid){
        return commodityService.queryCommodityAmountById(sid);
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
        commodity.setUpdateTime(new Date(new java.util.Date().getTime()));//更新时间
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
        commodity.setUpdateTime(new Date(new java.util.Date().getTime()));//更新时间
        return commodityService.updateById(commodity);
    }





}

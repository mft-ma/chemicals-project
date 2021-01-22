package com.example.chemicalsproject.supplier.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chemicalsproject.pojo.Supplier;
import com.example.chemicalsproject.supplier.mapper.UserMapper;
import com.example.chemicalsproject.supplier.service.UserService;
import com.example.chemicalsproject.supplier.util.SupplierDO;
import com.example.chemicalsproject.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private UserService userService;

    //查询供应商 &组合查询
    @RequestMapping("/querySupplier")
    @ResponseBody
    public List querySupplier(SupplierDO supplierDO){
        return supplierService.querySupplier(supplierDO);
    }

    //查询业务员表
    @ResponseBody
    @RequestMapping("/userQuery")
    public List userQuery(){
        return userService.list();
    }

    //供应商添加
    @RequestMapping("/addSupplier")
    @ResponseBody
    public boolean addSupplier(@RequestBody Supplier supplier){
        supplier.setStatus("未关联");
        return supplierService.save(supplier);
    }

    //删除
    @RequestMapping("/delSupplier")
    @ResponseBody
    public boolean delSupplier(Integer gid){
        return supplierService.removeById(gid);
    }

    //根据id查询
    @RequestMapping("/querySupplierById")
    @ResponseBody
    public Supplier querySupplierById(Integer gid){
        return supplierService.getById(gid);
    }

    public boolean updSupplier(Supplier supplier){
      return supplierService.updateById(supplier);
    }
}

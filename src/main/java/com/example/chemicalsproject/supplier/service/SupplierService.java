package com.example.chemicalsproject.supplier.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.pojo.Supplier;
import com.example.chemicalsproject.supplier.util.SupplierDO;
import com.example.chemicalsproject.supplier.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier> {
    @Autowired
    private SupplierMapper supplierMapper;

    public List querySupplier( SupplierDO supplierDO){
        return supplierMapper.querySupplier(supplierDO);
    }

    public  String IdFormat(Integer id){
        return String.format("AH%05d",id);
    }

    public boolean supplierUpd(String status,Integer gid){
        return supplierMapper.supplierUpd(status,gid);
    }
}

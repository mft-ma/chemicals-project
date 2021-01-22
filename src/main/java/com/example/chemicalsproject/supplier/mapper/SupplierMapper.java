package com.example.chemicalsproject.supplier.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.Supplier;
import com.example.chemicalsproject.supplier.util.SupplierDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SupplierMapper extends BaseMapper<Supplier> {

    public List querySupplier(SupplierDO supplierDO);
}

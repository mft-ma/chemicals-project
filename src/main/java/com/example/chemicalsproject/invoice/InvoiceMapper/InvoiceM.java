package com.example.chemicalsproject.invoice.InvoiceMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.Invoice;
import org.apache.ibatis.annotations.*;

@Mapper
public interface InvoiceM extends BaseMapper<Invoice> {
    @Update("update invoice set number=number+#{amount} where sid=#{sid}")
    boolean updAmount(Integer amount,Integer sid);
}

package com.example.chemicalsproject.invoice.InvoiceService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.invoice.InvoiceMapper.InvoiceM;
import com.example.chemicalsproject.invoice.util.InvoiceListConditionUtil;
import com.example.chemicalsproject.pojo.Invoice;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceS extends ServiceImpl<InvoiceM, Invoice> {
    @Autowired
    private InvoiceM invoiceM;
    /**
     * 根据条件查询 联合查询
     * @param page
     * @param condition
     * @return
     */
    public PageInfo<Invoice> queryAll(Integer page, Integer limit, InvoiceListConditionUtil condition) {
        System.out.println("service queryAll employee======" + condition);
        PageHelper.startPage(page, limit);
        QueryWrapper<Invoice> wrapper=new QueryWrapper<>();
        if(condition.getCommodityName()!=null & !"".equals(condition.getCommodityName())){
            wrapper.like("name",condition.getCommodityName());
        }
        if(condition.getInvoice_cas()!=null & !"".equals(condition.getInvoice_cas())){
            wrapper.eq("cas",condition.getInvoice_cas());
        }
        List<Invoice> invoices = invoiceM.selectList(wrapper);
        System.out.println("service queryAll invoices======");
        invoices.forEach(System.out::println);
        PageInfo<Invoice> info=new PageInfo<>(invoices);
        return info;
    }

    /**
     * 自动计算数量
     * @param amount
     * @param sid
     * @return
     */
    public boolean updAmount(Integer amount,Integer sid){
        return invoiceM.updAmount(amount,sid);
    }
}

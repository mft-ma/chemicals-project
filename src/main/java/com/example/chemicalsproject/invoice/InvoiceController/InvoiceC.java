package com.example.chemicalsproject.invoice.InvoiceController;

import com.alibaba.fastjson.JSONObject;
import com.example.chemicalsproject.invoice.InvoiceService.InvoiceS;
import com.example.chemicalsproject.invoice.util.InvoiceListConditionUtil;
import com.example.chemicalsproject.order.util.LayUiData;
import com.example.chemicalsproject.pojo.Invoice;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class InvoiceC {
    @Autowired
    private InvoiceS invoiceS;

    /**
     * 查询发票列表
     * @param invoice
     * @return
     */
    @RequestMapping("/invoiceList")
    public LayUiData invoiceList(@RequestBody InvoiceListConditionUtil invoice){
        System.out.println("invoice=="+invoice);
        Integer page=invoice.getPage();
        Integer limit=invoice.getLimit();
        PageInfo<Invoice> invoicePageInfo = invoiceS.queryAll(page, limit, invoice);
        List<Invoice> list = invoicePageInfo.getList();
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(invoicePageInfo.getTotal())));
        layUiData.setData(list);
        return layUiData;
    }

    /**
     * 删除发票记录
     * @param id
     * @return
     */
//    @RequiresPermissions(value = {"user:delete"},logical = Logical.OR)
    @RequestMapping("/delInvoice")
    public boolean delInvoice(String id) {
        System.out.println("delInvoice:"+id);
        return invoiceS.removeById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
//    @RequiresPermissions(value = {"user:batchDel"},logical = Logical.OR)
    @RequestMapping("/delBatchInvoice")
    public void delBatchInvoice(@RequestParam String ids) {
        System.out.println("ids===" + ids);
        String[] id = ids.split(",");
        invoiceS.removeByIds(Arrays.asList(id));
    }

    /**
     * 根据id查询单条invoice数据
     * @param id
     * @return
     */
    @RequestMapping("/queryInvoiceById")
    public Invoice queryInvoiceById(Integer id){
        return invoiceS.getById(id);
    }

    /**
     * 添加库存
     * @param json
     */
    //@RequiresPermissions(value = {"user:add"},logical = Logical.OR)
    @PostMapping("/addInvoice")
    public boolean addInvoice(@RequestBody String json) {
        Invoice invoice = JSONObject.parseObject(json).toJavaObject(Invoice.class);

        Date utilDate = new Date();//util.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//util.Date转sql.Date
        invoice.setCreatetime(sqlDate);

        System.out.println("addInventory的invoice============" + invoice);
        return invoiceS.save(invoice);
    }

    /**
     * 修改库存
     * @param json
     */
//    @RequiresPermissions(value = {"user:add"},logical = Logical.OR)
    @PostMapping("/udpInvoice")
    public boolean udpInvoice(@RequestBody String json) {
        Invoice invoice = JSONObject.parseObject(json).toJavaObject(Invoice.class);
        System.out.println("udpInventory的invoice============" + invoice);
        return invoiceS.updateById(invoice);
    }

    /**
     * 自动计算发票数量
     * @param amount
     * @param sid
     * @return
     */
    @RequestMapping("/updateAmount")
    public boolean addAmount(Integer amount,Integer sid){
        if(invoiceS.getById(sid).getNumber()==0){
            Invoice invoice = new Invoice();
            invoice.setNumber(amount);
            invoice.setSid(sid);
            return invoiceS.updateById(invoice);
        }else{
            return invoiceS.updAmount(amount,sid);
        }
    }
}

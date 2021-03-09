package com.example.chemicalsproject.sales.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chemicalsproject.order.service.InventoryOrderService;
import com.example.chemicalsproject.order.service.OrderService;
import com.example.chemicalsproject.order.util.LayUiData;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Inventory;
import com.example.chemicalsproject.pojo.Order;
import com.example.chemicalsproject.pojo.Sales;
import com.example.chemicalsproject.sales.service.SalesService;
import com.example.chemicalsproject.sales.util.SalesListConditionUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class SalesController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private InventoryOrderService inventoryOrderService;

    /**
     * 根据id查询一条退货记录
     * @param did
     * @return
     */
    @RequestMapping("/querySalesByID")
    public Sales querySalesByID(Integer did){
        return salesService.queryById(did);
    }

    /**
     * 根据 商品名称 或 物流编号 查询list
     * @param conditionUtil
     * @return
     */
    @RequestMapping("/salesList")
    public LayUiData salesList(@RequestBody SalesListConditionUtil conditionUtil){
        System.out.println("conditionUtil=="+conditionUtil);
        PageInfo<Sales> salesPageInfo = salesService.queryAll(conditionUtil);
        List<Sales> list = salesPageInfo.getList();
        for(Sales sale:list){
            sale.setFormatId(salesService.selfFormatId(sale.getDid()));
            sale.setId(sale.getDid());
            Order order=sale.getOrder();
            order.setFormatId(orderService.selfFormatId(order.getDid()));
            order.setId(order.getDid());
            sale.setOrder(order);
        }
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(salesPageInfo.getTotal())));
        layUiData.setData(list);
        return layUiData;
    }

    /**
     * 删除订单记录
     * @param id
     * @return
     */
//    @RequiresPermissions(value = {"user:delete"},logical = Logical.OR)
    @RequestMapping("/delSales")
    public boolean delSales(String id) {
        return salesService.removeById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
//    @RequiresPermissions(value = {"user:batchDel"},logical = Logical.OR)
    @RequestMapping("/delBatchSales")
    public boolean delBatchSales(@RequestParam String ids) {
        System.out.println("ids===" + ids);
        String[] id = ids.split(",");
        return salesService.removeByIds(Arrays.asList(id));
    }

    /**
     * 添加退货记录
     * 1.添加退货记录   2.把订单状态改为退货
     * @param sales
     * @return
     */
    @RequestMapping("/addSales")
    public boolean addSales(@RequestBody Sales sales){
        sales.setStatus(0);
        sales.setCreate_time(new Date(new java.util.Date().getTime()));
        boolean save = salesService.save(sales);
        boolean b = orderService.updateStatus("2", sales.getOrder_id());
        if(save & b){
            return true;
        }
        return false;
    }

    /**
     * 修改退货记录
     * @param sales
     * @return
     */
    @RequestMapping("/updateSales")
    public boolean updateSales(@RequestBody Sales sales){
        System.out.println("updateSales的sales:"+sales);
        return salesService.updateById(sales);
    }

    /**
     * 查询 已出库 的订单list
     * 数据库里 0未出库 1已出库 2已退货
     * 但是查询时，1代表未出库 2已出库 3已退货
     * @return
     */
    @RequestMapping("/getOrderList")
    public LayUiData getOrderList(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        System.out.println("page:"+page+"limit:"+limit);
        PageInfo<Order> orderPageInfo = orderService.queryOrderByStatus(page,limit,2);
        for(Order o:orderPageInfo.getList()){
            o.setFormatId(orderService.selfFormatId(o.getDid()));
        }
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(orderPageInfo.getTotal())));
        layUiData.setData(orderPageInfo.getList());
        System.out.println("orderPageInfo.getList().size():"+orderPageInfo.getList().size());
        return layUiData;
    }

    /**
     * 修改退货状态
     * 点击未收货按钮，实现：1.退货状态改为已收货  2.当前退货订单库存还原，提成改为0
     * @param did
     * @return
     */
    @RequestMapping("/updateSalesStatus")
    public boolean updateSalesStatus(Integer did){
        //退货状态改为已收到货
        Sales sales = salesService.queryById(did);
        System.out.println("updateSalesStatus的sales："+sales);
        sales.setStatus(1);
        boolean updateSalesById = salesService.updateById(sales);

        //提成归零
        Order order=sales.getOrder();
        order.setRoyalties(0);
        boolean updateOrder = orderService.updateOrder(order);

        //库存数量还原
        Integer inventory_id=order.getInvoice_id();
        QueryWrapper<Inventory> wrapper=new QueryWrapper<>();
        wrapper.eq("number",inventory_id);
        Inventory inventory = inventoryOrderService.getOne(wrapper);
        double newInventoryAmount = inventory.getAmount()+order.getAmount();
        inventory.setAmount(newInventoryAmount);
        boolean updateInventoryById = inventoryOrderService.updateById(inventory);

        if(updateSalesById & updateOrder &updateInventoryById){
            return true;
        }
        return false;
    }
}

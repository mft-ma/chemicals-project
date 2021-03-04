package com.example.chemicalsproject.inventory.web;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chemicalsproject.inventory.service.InventoryService;
import com.example.chemicalsproject.inventory.util.InventoryListConditionUtil;
import com.example.chemicalsproject.order.util.LayUiData;
import com.example.chemicalsproject.pojo.Commodity;
import com.example.chemicalsproject.pojo.Inventory;
import com.example.chemicalsproject.pojo.Order;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * 查询库存列表
     * @param inventory
     * @return
     */
    @RequestMapping("/inventoryList")
    public LayUiData inventoryList(@RequestBody InventoryListConditionUtil inventory){
        System.out.println("inventory=="+inventory);
        Integer page=inventory.getPage();
        Integer limit=inventory.getLimit();
        PageInfo<Inventory> inventoryPageInfo = inventoryService.queryAll(page, limit, inventory);
        List<Inventory> list = inventoryPageInfo.getList();
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(inventoryPageInfo.getTotal())));
        layUiData.setData(list);
        return layUiData;
    }

    /**
     * 查询0库存数据列表
     * @return
     */
    @RequestMapping("/queryAmountList")
    public LayUiData queryAmountList(@RequestBody InventoryListConditionUtil inventory){
        Integer page=inventory.getPage();
        Integer limit=inventory.getLimit();
        PageInfo<Inventory> inventoryPageInfo = inventoryService.queryAmountList(page, limit);
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(inventoryPageInfo.getTotal())));
        layUiData.setData(inventoryPageInfo.getList());
        return layUiData;
    }

    /**
     * 删除库存记录
     * @param id
     * @return
     */
//    @RequiresPermissions(value = {"user:delete"},logical = Logical.OR)
    @RequestMapping("/delInventory")
    public boolean delInventory(String id) {
        System.out.println("delinventory:"+id);
        return inventoryService.removeById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
//    @RequiresPermissions(value = {"user:batchDel"},logical = Logical.OR)
    @RequestMapping("/delBatchInventory")
    public void delBatchInventory(@RequestParam String ids) {
        System.out.println("ids===" + ids);
        String[] id = ids.split(",");
        inventoryService.removeByIds(Arrays.asList(id));
    }

    /**
     * 根据id查询单条inventory数据
     * @param id
     * @return
     */
    @RequestMapping("/queryInventoryById")
    public Inventory queryInventoryById(Integer id){
        return inventoryService.getById(id);
    }

    /**
     * 添加库存
     * @param json
     * @throws IOException
     */
//    @RequiresPermissions(value = {"user:add"},logical = Logical.OR)
    @PostMapping("/addInventory")
    public boolean addInventory(@RequestBody String json) {
        Inventory inventory = JSONObject.parseObject(json).toJavaObject(Inventory.class);

        Date utilDate = new Date();//util.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//util.Date转sql.Date
        inventory.setCreate_time(sqlDate);

        System.out.println("addInventory的inventory============" + inventory);
        return inventoryService.save(inventory);
    }

    /**
     * 修改库存
     * @param json
     * @throws IOException
     */
//    @RequiresPermissions(value = {"user:add"},logical = Logical.OR)
    @PostMapping("/udpInventory")
    public boolean udpInventory(@RequestBody String json) {
        Inventory inventory = JSONObject.parseObject(json).toJavaObject(Inventory.class);
        Date utilDate = new Date();//util.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//util.Date转sql.Date
        inventory.setUpdate_time(sqlDate);
        System.out.println("udpInventory的inventory============" + inventory);
        return inventoryService.updateById(inventory);
    }

    /**
     * 添加库存数量
     * @param amount
     * @param kid
     * @return
     */
    @RequestMapping("/addAmount")
    public boolean addAmount(Integer amount,Integer kid){
        if(inventoryService.getById(kid).getAmount()==0){
            Inventory inventory = new Inventory();
            inventory.setAmount(amount);
            inventory.setKid(kid);
            return inventoryService.updateById(inventory);
        }else{
            return inventoryService.updAddAmount(amount,kid);
        }
    }

    /**
     * 减少库存数量
     * @param amount
     * @param kid
     * @return
     */
    @RequestMapping("/reduceAmount")
    public boolean reduceAmount(Integer amount,Integer kid){
        if(inventoryService.getById(kid).getAmount()==0){
            Inventory inventory = new Inventory();
            inventory.setAmount(-amount);
            inventory.setKid(kid);
            return inventoryService.updateById(inventory);
        }else{
            return inventoryService.updReduceAmount(amount,kid);
        }

    }

}

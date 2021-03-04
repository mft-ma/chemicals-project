package com.example.chemicalsproject.order.web;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chemicalsproject.order.service.CommodityOrderService;
import com.example.chemicalsproject.order.service.InventoryOrderService;
import com.example.chemicalsproject.order.util.ImgUtils;
import com.example.chemicalsproject.order.util.LayUiData;
import com.example.chemicalsproject.order.util.OrderListConditionUtil;
import com.example.chemicalsproject.pojo.Commodity;
import com.example.chemicalsproject.pojo.Inventory;
import com.example.chemicalsproject.pojo.Order;
import com.example.chemicalsproject.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private InventoryOrderService inventoryService;
    @Autowired
    private CommodityOrderService commodityService;

    /**
     * 查询订单列表
     * @param order
     * @return
     */
    @RequestMapping("/orderList")
    public LayUiData orderList(@RequestBody OrderListConditionUtil order){
        System.out.println("order=="+order);
        Integer page=order.getPage();
        Integer limit=order.getLimit();
        PageInfo<Order> orderPageInfo = orderService.queryAll(page, limit, order);
        List<Order> list = orderPageInfo.getList();
        for(Order o:list){
            o.setFormatId(orderService.selfFormatId(o.getDid()));
            o.setId(o.getDid());
        }
        LayUiData layUiData = new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.valueOf(String.valueOf(orderPageInfo.getTotal())));
        layUiData.setData(list);
        return layUiData;
    }

    /**
     * 订单生成标签
     * @param name
     * @param amount
     * @param response
     * @throws IOException
     */
    @RequestMapping("/createTag")
    public void createTag(String name, String amount, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        //获取批号（当前日期减10天，格式：20201214）
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-10);
        String number=new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        //基础信息
        BufferedImage image = ImgUtils.imgSave(name, amount + "千克", number, "广州远达新材料有限公司","19973563344");
        //以png格式向客户端发送
        response.setHeader("Content-Disposition","attachment;filename=image.png");
        ServletOutputStream os=response.getOutputStream();
        ImageIO.write(image,"PNG",os);
        os.flush();
        os.close();
    }

    /**
     * 删除订单记录
     * @param id
     * @return
     */
//    @RequiresPermissions(value = {"user:delete"},logical = Logical.OR)
    @RequestMapping("/delOrder")
    public boolean delOrder(String id) {
        return orderService.delOrder(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
//    @RequiresPermissions(value = {"user:batchDel"},logical = Logical.OR)
    @RequestMapping("/delBatchOrder")
    public void delBatch(@RequestParam String ids) {
        System.out.println("ids===" + ids);
        String[] id = ids.split(",");
        System.out.println(id[0]);
        for (int i=0;i<id.length;i++){
            orderService.delOrder(id[i]);
        }
    }

    /**
     * 点击未出库，修改订单库存状态为已出库&库存数量-1
     * @param did
     * @param cas
     */
    @RequestMapping("/updateStatus")
    public boolean updateStatus(Integer did,String cas,Integer amount){
//        ?did=482&cas=003&amount=10
        System.out.println("did:"+did+"cas:"+cas+"amount:"+amount);
        //修改状态为已出库
        boolean b = orderService.updateStatus("1", did);
        //根据cas修改库存数量
        Inventory inventory=new Inventory();
        //根据cas查询库存表id
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("cas",cas);
        Inventory one = inventoryService.getOne(queryWrapper);

        inventory.setKid(one.getKid());
        inventory.setAmount(one.getAmount()-amount);
        boolean b1 = inventoryService.updateById(inventory);
        if(b & b1){
            return true;
        }
        return false;
    }

    /**
     * 根据did查询单条order数据
     * @param did
     * @return
     */
    @RequestMapping("/queryOrderById")
    public Order queryOrderById(Integer did){
        return orderService.queryById(did);
    }

    /**
     * 根据commodityName或cas查询商品
     * @param commodityName
     * @param cas
     * @return
     */
    @RequestMapping("/queryCommodityList")
    public List<Commodity> queryCommodityList(String commodityName,String cas){
        System.out.println("commodity:"+commodityName+"cas:"+cas);
        QueryWrapper<Commodity> queryWrapper=new QueryWrapper();
        if(commodityName!=null & !"".equals(commodityName)){
            queryWrapper.like("name",commodityName);
        }
        if(cas!=null & !"".equals(cas)){
            queryWrapper.like("cas",cas);
        }
        List<Commodity> list = commodityService.list(queryWrapper);
        System.out.println("queryCommodityList的List<Commodity>：");
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 查询commodity list
     * @return
     */
    @RequestMapping("/commodityList")
    public List<Commodity> commodityList(){
        return commodityService.list();
    }

    /**
     *根据commodity_id查询对应的inventory_id库存编号
     * @param id
     * @return
     */
    @RequestMapping("/queryInventoryByCommodity")
    public List<Inventory> queryInventoryByCommodity(String id){
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cas",commodityService.getById(id).getCas());
        List<Inventory> list = inventoryService.list(queryWrapper);
        return list;
    }

    /**
     * 添加订单
     * @param json
     * @throws IOException
     */
//    @RequiresPermissions(value = {"user:add"},logical = Logical.OR)
    @PostMapping("/addOrder")
    public boolean addOrder(@RequestBody String json) {
        Order order = JSONObject.parseObject(json).toJavaObject(Order.class);

        Date utilDate = new java.util.Date();//util.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//util.Date转sql.Date
        order.setCreate_time(sqlDate);

        Integer user_id = order.getUser_id();//业务员id
        Integer commodity_id = order.getCommodity_id();//商品id

        double amount = order.getAmount();//商品数量
        double price = order.getPrice();//商品单价
        double cost_price = order.getCost_price();//商品成本
        double other_cost = order.getOther_cost();//商品其他成本
        double sumPrice=amount*price;//总价
        double sumCost=amount*cost_price+other_cost;//总成本
        //计算提成
        Commodity commodity = commodityService.getById(commodity_id);
        if(commodity.getUserId()==user_id){//A的商品&A提交的订单
            //提成=(总价-总成本-其他成本)*87%*0.4
            order.setRoyalties((sumPrice-sumCost)*0.87*0.4);
        }else{
            QueryWrapper<Commodity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",user_id);
            //查询当前userID的所有商品
            List<Commodity> list = commodityService.list(queryWrapper);
            List<Integer> list1=new ArrayList<>();
            for(Commodity commodity1:list){
                list1.add(commodity1.getSid());
            }
            //当前用户卖的商品在当前用户商品的范围内 == A的商品&不是A提交的订单
            if(list1.contains(commodity_id)){
                //提成=(总价-总成本-其他成本)*87%*0.1
                order.setRoyalties((sumPrice-sumCost)*0.87*0.1);
            }else{//否则当前用户卖的商品不在当前用户商品的范围内 == 不是A的商品&是A提交的订单
                //提成=(总价-总成本-其他成本)*87%*0.3
                order.setRoyalties((sumPrice-sumCost)*0.87*0.3);
            }
        }
        System.out.println("addOrder的order============" + order);
        return orderService.saveOrder(order);
    }

    /**
     * 修改订单
     * @param json
     * @throws IOException
     */
//    @RequiresPermissions(value = {"user:add"},logical = Logical.OR)
    @PostMapping("/udpOrder")
    public boolean udpOrder(@RequestBody String json) {
        Order order = JSONObject.parseObject(json).toJavaObject(Order.class);

        Integer user_id = order.getUser_id();//业务员id
        Integer commodity_id = order.getCommodity_id();//商品id

        double amount = order.getAmount();//商品数量
        double price = order.getPrice();//商品单价
        double cost_price = order.getCost_price();//商品成本
        double other_cost = order.getOther_cost();//商品其他成本
        double sumPrice=amount*price;//总价
        double sumCost=amount*cost_price+other_cost;//总成本
        //计算提成
        Commodity commodity = commodityService.getById(commodity_id);
        if(commodity.getUserId()==user_id){//A的商品&A提交的订单
            //提成=(总价-总成本-其他成本)*87%*0.4
            order.setRoyalties((sumPrice-sumCost)*0.87*0.4);
        }else{
            QueryWrapper<Commodity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",user_id);
            //查询当前userID的所有商品
            List<Commodity> list = commodityService.list(queryWrapper);
            List<Integer> list1=new ArrayList<>();
            for(Commodity commodity1:list){
                list1.add(commodity1.getSid());
            }
            //当前用户卖的商品在当前用户商品的范围内 == A的商品&不是A提交的订单
            if(list1.contains(commodity_id)){
                //提成=(总价-总成本-其他成本)*87%*0.1
                order.setRoyalties((sumPrice-sumCost)*0.87*0.1);
            }else{//否则当前用户卖的商品不在当前用户商品的范围内 == 不是A的商品&是A提交的订单
                //提成=(总价-总成本-其他成本)*87%*0.3
                order.setRoyalties((sumPrice-sumCost)*0.87*0.3);
            }
        }
        System.out.println("updateOrder的order============" + order);
        return orderService.updateOrder(order);
    }

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    @RequestMapping("/queryCommodityById")
    public Commodity queryCommodityById(String id){
        return commodityService.getById(id);
    }

    /**
     * 添加库存信息
     * @param inventory
     * @return
     */
    @RequestMapping("/addInventoryOrder")
    public boolean addInventory(@RequestBody Inventory inventory){
        System.out.println("addInventory的inventory："+inventory);
        return inventoryService.save(inventory);
    }
}

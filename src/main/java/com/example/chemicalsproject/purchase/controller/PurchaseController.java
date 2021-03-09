package com.example.chemicalsproject.purchase.controller;

import com.example.chemicalsproject.pojo.Purchase;
import com.example.chemicalsproject.purchase.service.PurchaseService;
import com.example.chemicalsproject.purchase.util.PurchaseDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping("/queryPurchase")
    @ResponseBody
    public List queryPurchase(PurchaseDo purchaseDo){
        List<Purchase> list= purchaseService.queryPurchase(purchaseDo);
        for(Purchase purchase:list){
            purchase.setFormatId(purchaseService.IdFormat(purchase.getCid()));
        }
        return list;
    }

    /**
     * 采购添加
     * @param purchase
     * @return
     */
    @RequestMapping("/addPurchase")
    @ResponseBody
    public boolean addPurchase(@RequestBody Purchase purchase){
        double sumPrice=purchase.getPrice()*purchase.getAmount();//总价
        purchase.setSumPrice(sumPrice);
        return  purchaseService.save(purchase);
    }

    @RequestMapping("/delPurchase")
    @ResponseBody
    public boolean delPurchase(Integer cid){
        return purchaseService.removeById(cid);
    }

    @RequestMapping("/queryPurchaseById")
    @ResponseBody
    public Purchase queryPurchaseById(Integer cid){
        return purchaseService.getById(cid);
    }

    @RequestMapping("/updPurcahse")
    @ResponseBody
    public boolean updPurcahse(@RequestBody Purchase purchase){
        return purchaseService.updateById(purchase);
    }

    @RequestMapping("/updPurcahseTrackingNumber")
    @ResponseBody
    public boolean updatePurchaseTrackingNumber(@RequestBody Purchase purchase){
        return purchaseService.updatePurchaseTrackingNumber(purchase.getTrackingNumber(),purchase.getCid());
    }
}

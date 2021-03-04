package com.example.chemicalsproject.purchase.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.pojo.Purchase;
import com.example.chemicalsproject.purchase.mapper.PurchaseMapper;
import com.example.chemicalsproject.purchase.util.PurchaseDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseService extends ServiceImpl<PurchaseMapper, Purchase> {
    @Autowired
    private PurchaseMapper purchaseMapper;

    public List queryPurchase(PurchaseDo purchaseDo){
        return  purchaseMapper.queryPurchase(purchaseDo);
    }

    /**
     * 修改物流编号
     * @param tracking_number
     * @param cid
     * @return
     */
    public boolean updatePurchaseTrackingNumber(String tracking_number,Integer cid){
        return purchaseMapper.updatePurchaseTrackingNumber(tracking_number,cid);
    }
}

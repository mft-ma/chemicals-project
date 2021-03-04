package com.example.chemicalsproject.purchase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.Purchase;
import com.example.chemicalsproject.purchase.util.PurchaseDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {
    public List queryPurchase(PurchaseDo purchaseDo);

    @Update("update purchase set tracking_number=#{tracking_number} where cid=#{cid}")
    boolean updatePurchaseTrackingNumber(String tracking_number,Integer cid);
}

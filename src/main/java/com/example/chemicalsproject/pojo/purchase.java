package com.example.chemicalsproject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "purchase")
public class purchase {
    @TableId(value = "cid",type = IdType.AUTO)
    private Integer cid;  //主键
    private Integer user_id;  //业务员id
    private String name;  //产品名称
    private String cas;  //cas号
    private Integer amount;  //数量
    private double price;  //单价
    private String price_status;  //0  单价（含税）、1  单价（不含税）
    private double sum_price;  //总价
    private Integer status;  //收货状态  0未收货   1确认收货
    private String supplier_name;  //供应商姓名
    private String supplier_phone;  //供应商电话
    private String tracking_number;  //物流单号
    private Data create_time;  //创建日期
    private Integer ann;  //1未收到发票，2收到发票
}

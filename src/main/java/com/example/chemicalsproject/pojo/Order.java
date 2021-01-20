package com.example.chemicalsproject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order")
public class Order {
    @TableId(value = "did",type = IdType.AUTO)
    private Integer did;//主键
    private Integer user_id;//业务员id
    private double amount;//数量
    private double price;//单价
    private double cost_price;//成本价
    private double royalties;//提成
    private double other_cost;//其他成本
    private Integer bill;//有无票据 0无票据 1有票据
    private String bill_info;//有票据，填写开票资料
    private Integer commodity_id;//商品id
    private Integer invoice_id;//库存编号
    private Integer status;//库存状态 0未出库 1已出库 2退货
    private String user_name;//客户名称
    private String address;//地址
    private String phone;//联系电话
    private Date create_time;//创建时间
    private String remarks;//备注

}

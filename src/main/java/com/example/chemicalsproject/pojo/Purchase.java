package com.example.chemicalsproject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "purchase")
public class Purchase {
    @TableId(value = "cid",type = IdType.AUTO)
    private int cid;
    private int userId;
    private String name;
    private String cas;
    private int amount;
    private double price;
    private String priceStatus;
    private double sumPrice;
    private String status;
    private String supplierName;
    private String supplierPhone;
    private String trackingNumber;
    private Date createTime;
    private int ann;
    @TableField(exist = false)
    private User user;
}

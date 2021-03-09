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
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "commodity")
public class Commodity {

    @TableId(value = "sid",type = IdType.AUTO)
    private Integer sid;
    private String name;
    private String cas;
    private Integer userId;
    private String priceInfo;
    private String commodityInfo;
    private String imgStatus;
    private String imgPath;
    private String fileStatus;
    private String filePath;
    private Integer supplierId;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Inventory inventory;
    @TableField(exist = false)
    private String formatId;
}

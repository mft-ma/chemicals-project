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
@TableName(value = "supplier")
public class Supplier {
    @TableId(value = "gid",type = IdType.AUTO)
    private Integer gid;
    private Integer userId;
    private String name;//供应商名称
    private String phone;
    private String wechat;
    private String status;
    private Date createTime;
    private String remark;//备注
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private String formatId;
}

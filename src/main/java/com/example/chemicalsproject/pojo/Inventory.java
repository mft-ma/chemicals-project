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
@TableName(value = "inventory")
public class Inventory {
    @TableId(value = "kid",type = IdType.AUTO)
    private Integer kid;//主键
    private String number;
    private String name;
    private String cas;
    private Date create_time;
    private Date update_time;
    private double amount;
    private String remark;
}

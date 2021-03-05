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
@TableName(value = "Invoice")
public class Invoice {
    @TableId(value = "sid",type = IdType.AUTO)
    private Integer sid;
    private String name;
    private String cas;
    private Integer number;
    private Integer price;
    @TableField(value = "create_time")
    private Date createtime;
    private String unit;

}

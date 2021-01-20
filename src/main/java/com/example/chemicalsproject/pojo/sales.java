package com.example.chemicalsproject.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sales")
public class sales {
    private Integer did;//主键
    private Integer user_id;//业务员id
    private Integer order_id;//订单id
    private String tracking_number;//物流编号
    private String tracking_name;//物流公司名称
    private Integer status;//退货状态 0未收到退货 1已收到
    private Date create_time;//创建时间
}

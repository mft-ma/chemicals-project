package com.example.chemicalsproject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.soap.Text;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "supplier")
public class supplier {
    @TableId(value = "gid",type = IdType.AUTO)
    private Integer gid;
    private Integer user_id;
    private String name;
    private String phone;
    private String wechat;
    private String status;
    private Date create_time;
    private Text remark;
}

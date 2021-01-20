package com.example.chemicalsproject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "users")
public class User {
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;
    @TableField(value = "user_name")
    private String username;
    private String password;
    private Integer type;

    public User(String user_name, String password){
        this.username=user_name;
        this.password=password;
    }
}

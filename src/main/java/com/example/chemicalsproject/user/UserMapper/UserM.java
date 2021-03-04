package com.example.chemicalsproject.user.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserM extends BaseMapper<User> {
}

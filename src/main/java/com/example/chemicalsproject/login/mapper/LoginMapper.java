package com.example.chemicalsproject.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chemicalsproject.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper extends BaseMapper<User> {
}

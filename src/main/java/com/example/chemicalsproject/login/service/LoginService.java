package com.example.chemicalsproject.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.login.mapper.LoginMapper;
import com.example.chemicalsproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoginService extends ServiceImpl<LoginMapper, User> {

    @Autowired
    private LoginMapper userMapper;

    /**
     * 根据名字查询此用户是否存在   登录
     *
     * @param username
     * @return
     */
    public boolean queryName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        List<User> employeeList = userMapper.selectList(queryWrapper);
        if (employeeList.size() != 0) {
            return true;
        } else {
            return false;
        }
    }
}

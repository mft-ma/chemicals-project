package com.example.chemicalsproject.user.UserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.pojo.User;
import com.example.chemicalsproject.user.UserMapper.UserM;
import com.example.chemicalsproject.user.util.UserListConditionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserS extends ServiceImpl<UserM, User> {
    @Autowired
    private UserM userM;
    /**
     * 根据条件查询 联合查询
     * @param page
     * @param condition
     * @return
     */
    public PageInfo<User> queryAll(Integer page, Integer limit, UserListConditionUtil condition) {
        System.out.println("service queryAll employee======" + condition);
        PageHelper.startPage(page, limit);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        List<User> userList = userM.selectList(wrapper);

        System.out.println("service queryAll invoices======");
        userList.forEach(System.out::println);
        PageInfo<User> info=new PageInfo<>(userList);
        return info;
    }
}

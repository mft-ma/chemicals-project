package com.example.chemicalsproject.user.UserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.pojo.User;
import com.example.chemicalsproject.user.UserMapper.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserS extends ServiceImpl<UserM, User> {
    /*@Autowired(required = false)
    private UserM userM;*/
}

package com.example.chemicalsproject.supplier.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.pojo.User;
import com.example.chemicalsproject.supplier.mapper.UserForSupplierMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserForSupplierService extends ServiceImpl<UserForSupplierMapper, User> {
}

package com.example.chemicalsproject.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chemicalsproject.order.mapper.InventoryMapper;
import com.example.chemicalsproject.pojo.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryService extends ServiceImpl<InventoryMapper, Inventory> {
}

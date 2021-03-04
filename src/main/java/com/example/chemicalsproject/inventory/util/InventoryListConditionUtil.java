package com.example.chemicalsproject.inventory.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryListConditionUtil {
    private Integer page;
    private Integer limit;
    private String commodityName;
    private String inventory_cas;
    private String inventoryNumber;
    private Integer user_id;
    private Integer user_rule;
}

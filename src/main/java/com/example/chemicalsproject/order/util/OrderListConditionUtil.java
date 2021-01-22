package com.example.chemicalsproject.order.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListConditionUtil {
    private Integer page;
    private Integer limit;
    private String commodityName;
    private String startTime;
    private String endTime;
    private Integer commodityUser;
}

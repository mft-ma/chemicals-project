package com.example.chemicalsproject.sales.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesListConditionUtil {
    private Integer page;
    private Integer limit;
    private String commodityName;
    private String tracking_number;
    private Integer commodityUser;
}

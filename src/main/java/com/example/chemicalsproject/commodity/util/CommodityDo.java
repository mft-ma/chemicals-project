package com.example.chemicalsproject.commodity.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommodityDo {
    private String name;
    private String cas;
    private String startdate;
    private String enddate;
}

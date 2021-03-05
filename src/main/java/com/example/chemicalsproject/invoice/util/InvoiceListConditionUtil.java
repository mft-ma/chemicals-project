package com.example.chemicalsproject.invoice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListConditionUtil {
    private Integer page;
    private Integer limit;
    private String commodityName;
    private String invoice_cas;
    private Integer user_id;
    private Integer user_rule;
}

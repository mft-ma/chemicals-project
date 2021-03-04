package com.example.chemicalsproject.purchase.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDo {
    private String name;
    private String cas;
    private String supplierName;
    private String trackingNumber;
}

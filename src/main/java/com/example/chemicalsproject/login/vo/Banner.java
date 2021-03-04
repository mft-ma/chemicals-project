package com.example.chemicalsproject.login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {

    private String title;//图片title
    private String imageUrl;//图片url
    private String linkUrl;//点击图片跳转页面链接
}

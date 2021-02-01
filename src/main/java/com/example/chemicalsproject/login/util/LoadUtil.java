package com.example.chemicalsproject.login.util;

import com.example.chemicalsproject.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadUtil {
    private Integer id;
    private String name;
    private String password;
    private String token;
    private Integer code;
    private Integer rules;

    public LoadUtil(String name, String password, String token, Integer code) {
        this.name = name;
        this.password = password;
        this.token = token;
        this.code = code;
    }
}

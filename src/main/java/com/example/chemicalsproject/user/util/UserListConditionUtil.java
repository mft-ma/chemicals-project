package com.example.chemicalsproject.user.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListConditionUtil {
    private Integer page;
    private Integer limit;
    private Integer user_id;
    private Integer user_rule;
}

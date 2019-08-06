package com.vincent.demo.orika.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AuthorEntity {

    private Integer authorId;
    private String authorName;
    @JSONField(format = "yyyy-MM-dd")
    private Date authorBirthday;
    private String desc;
}

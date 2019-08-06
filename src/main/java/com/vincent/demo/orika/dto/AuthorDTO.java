package com.vincent.demo.orika.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthorDTO {

    private Long authorId;
    private String authorName;
    @JSONField(format = "yyyy-MM-dd")
    private Date authorBirthday;
    private String bookMark;
}

package com.vincent.demo.orika.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AuthorDTO {

    private String authorName;

    private Date authorBirthday;
}

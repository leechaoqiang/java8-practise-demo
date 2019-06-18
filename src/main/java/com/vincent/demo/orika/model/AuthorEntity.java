package com.vincent.demo.orika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AuthorEntity {

    private String authorName;

    private Date authorBirthday;
}

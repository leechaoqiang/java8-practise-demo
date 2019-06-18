package com.vincent.demo.orika.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorReqBo {
    private String country;
    private List<AuthorEntity> list;
}

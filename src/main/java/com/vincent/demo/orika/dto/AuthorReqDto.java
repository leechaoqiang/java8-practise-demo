package com.vincent.demo.orika.dto;


import lombok.Data;

import java.util.List;

@Data
public class AuthorReqDto {
    private String country;
    private List<AuthorDTO> list;
}

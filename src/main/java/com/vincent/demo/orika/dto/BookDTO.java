package com.vincent.demo.orika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookDTO {

    private String bookName;

    private AuthorDTO author;

    private String bookInformation;
    private Date publishDate;

}

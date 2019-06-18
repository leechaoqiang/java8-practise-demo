package com.vincent.demo.orika.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookEntity {
    private String bookName;
    private AuthorEntity author;
    private String bookInformation;
    private Date publishDate;
}

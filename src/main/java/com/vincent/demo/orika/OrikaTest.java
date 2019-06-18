package com.vincent.demo.orika;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.vincent.demo.orika.dto.AuthorReqDto;
import com.vincent.demo.orika.dto.BookDTO;
import com.vincent.demo.orika.model.AuthorEntity;
import com.vincent.demo.orika.model.AuthorReqBo;
import com.vincent.demo.orika.model.BookEntity;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class OrikaTest {

    public static void main(String[] args){
//        testBookEntity2Dto();
        testAuthorReqBo2Dto();

    }

    public static void testBookEntity2Dto(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        MapperFacade mapper = mapperFactory.getMapperFacade();
        Instant instant =  LocalDate.of(1952, Month.MARCH, 11).atStartOfDay(ZoneId.systemDefault()).toInstant();
        BookEntity bookEntity = new BookEntity(
                "银河系漫游指南",
                AuthorEntity.builder().authorName("道格拉斯·亚当斯").authorBirthday(Date.from(instant)).build(),
                "{\"ISBN\": \"9787532754687\", \n \"page\": 279\n }",
                new Date()
        );
        System.out.println("bookEntity: "+JSON.toJSONString(bookEntity));
        final BookDTO bookDTO = mapper.map(bookEntity, BookDTO.class);

        System.out.println("bookDTO: "+JSON.toJSONString(bookDTO));

    }

    public static void testAuthorReqBo2Dto(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Instant instant =  LocalDate.of(1952, Month.MARCH, 11).atStartOfDay(ZoneId.systemDefault()).toInstant();
        AuthorReqBo reqBo = new AuthorReqBo("Russia", Lists.newArrayList(

                AuthorEntity.builder().authorName("普希金").authorBirthday(Date.from(instant)).build(),
                AuthorEntity.builder().authorName("查理芒格").authorBirthday(Date.from(instant)).build()
        ));
        System.out.println("AuthorReqBo: "+JSON.toJSONString(reqBo));
        AuthorReqDto authorReqDto =    mapper.map(reqBo, AuthorReqDto.class);

        System.out.println("authorReqDto: "+JSON.toJSONString(authorReqDto));

    }
}

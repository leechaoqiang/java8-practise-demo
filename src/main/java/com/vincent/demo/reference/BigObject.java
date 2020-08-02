package com.vincent.demo.reference;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vincent.li
 * @Description 测试大对象
 * @since 2020/6/28
 */
@Getter
@Setter
class BigObject {
    private byte[] values;
    private String name;

    public BigObject(String name){
        this.name = name;
        values = new byte[1024 * 1024 * 1000];
    }
}

package com.vincent.demo.spi;

import com.vincent.demo.spi.interfaces.Search;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> searchs = s.iterator();
        while(searchs.hasNext()){
            Search sea = searchs.next();
            sea.search("hello world.");
        }
    }

}

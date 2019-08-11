package com.lbc.corgi;

import com.google.common.collect.Maps;

import java.util.HashMap;

public class Father {

    protected final HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();

    public Father() {
        System.out.println("father : " + this);
        System.out.println("father class : " + this.getClass());
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();

    }

}

class Children1 extends Father {
    public Children1() {
        System.out.println("Children : " + this);
        System.out.println("Children class : " + this.getClass());

    }

    public void test() {
        objectObjectHashMap.put("q", "1");
    }
}


class Children2 extends Father {
    public Children2() {
        System.out.println("Children : " + this);
        System.out.println("Children class : " + this.getClass());

    }

    public void test() {
        objectObjectHashMap.put("w", "2");
    }

    public static void main(String[] args) {
        Children1 children1 = new Children1();
        children1.test();
        Children2 children2 = new Children2();
        children2.test();
    }
}
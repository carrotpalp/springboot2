package com.springboot2.carrot.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class Person {
    private int age;
    private String name;
    private int sex;

    Map map = new HashMap();
    @JsonAnySetter
    private void other(String property,Object value){
        map.put(property,value);
    }

    @JsonAnyGetter
    private Map<String,Object> getOtherProperties(){
        return map;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}

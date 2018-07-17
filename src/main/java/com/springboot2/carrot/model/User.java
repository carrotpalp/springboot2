package com.springboot2.carrot.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class User {
    public interface Update{}
    public interface Add{}

    @Null(groups = {Add.class})
    @NotNull(groups = {Update.class},message = "更新操作id不能为空")
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

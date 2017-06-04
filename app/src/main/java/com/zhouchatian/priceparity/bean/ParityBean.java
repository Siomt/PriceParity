package com.zhouchatian.priceparity.bean;

/**
 * Created by  Mr.Robot on 2017/6/4.
 * GitHub:https://github.com/TheSadFrog
 */

public class ParityBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ParityBean{" +
                "name='" + name + '\'' +
                '}';
    }
}

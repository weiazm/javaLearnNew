/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.hongyan.learn.common.catalog.inherit;

/**
 * @title Father
 * @desc description
 * @author hongyan
 * @date 2016年8月4日
 * @version version
 */
public class Father {

    static {
        System.out.println("im father static block!");
    }
    @SuppressWarnings("unused")
    private int age = 35;

    {
        System.out.println("im father code block!");
    }

    public Father() {
        System.out.println("father's constructor");
    }
}

/*
 * Baijiahulian.com Inc.
 * Copyright (c) 2014-${year} All Rights Reserved.
 */
package com.hongyan.learn.test.service.hag;

import com.baijia.yunying.hag.service.HagService;
import com.hongyan.learn.config.ApplicationConfig;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Created by weihongyan on 9/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class HagServiceTest {

    @Autowired
    private HagService hagService;

    @Test
    @SneakyThrows
    public void test() {
        Assert.notNull(hagService);
        System.out.println(hagService.getEntityKeyOfResource("train_resource_1"));//根据资源名获取名单
        System.out.println(hagService.getEntityKeyOfResource("tianxiao_try_noDisplay_version"));//根据资源名获取名单
        String version = null;
        Thread.sleep(10000);
        System.out.println(hagService.hasPermission(version,0,"tianxiao_try_noDisplay_version"));
        System.out.println(hagService.hasPermission("1.5",0,"tianxiao_try_noDisplay_version"));
//        WebResponse<String> response = hagService.addResource(1000002L,"fcefac09183c49af91849d18d1e",resource);
//        System.out.println(response);
    }
}

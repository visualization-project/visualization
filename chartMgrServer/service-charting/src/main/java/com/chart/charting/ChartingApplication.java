package com.chart.charting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Caiqin
 * @Date: 2019/3/21 21:59
 */
@SpringBootApplication
@EntityScan("com.chart.domain.charting")//扫描实体类
@ComponentScan(basePackages = {"com.chart.api"})//扫描接口,扫描本项目下所有的类
@ComponentScan(basePackages = {"com.chart.charting"})
public class ChartingApplication {
    public static void main(String[] args) throws Exception{
        SpringApplication.run( ChartingApplication.class,args );
    }
}

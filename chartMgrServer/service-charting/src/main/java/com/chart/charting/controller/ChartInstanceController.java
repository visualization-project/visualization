package com.chart.charting.controller;

import com.chart.api.charting.ChartInstanceControllerApi;
import com.chart.charting.service.ChartInstanceService;
import com.chart.domain.charting.ChartInstance;
import com.chart.framework.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:31
 */
@RestController
@RequestMapping("/chart-instance")
public class ChartInstanceController implements ChartInstanceControllerApi {

    @Autowired
    private ChartInstanceService chartInstanceService;

    @Override
    @GetMapping("/list")
    public List<ChartInstance> findAll() {
        return chartInstanceService.findAll();
    }
}

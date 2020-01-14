package com.chart.charting.controller;

import com.chart.api.charting.ChartInstanceControllerApi;
import com.chart.charting.service.ChartInstanceService;
import com.chart.domain.charting.ChartInstance;
import com.chart.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult findAll() {
        return chartInstanceService.findAll();
    }

    @Override
    @PostMapping("/add")
    public ResponseResult addChartInstance(@RequestBody ChartInstance chartInstance) {
        return chartInstanceService.addChartInstance(chartInstance);
    }

    @Override
    @GetMapping("/find/{id}")
    public ResponseResult findChartInstance(@PathVariable("id") String id) {
        return chartInstanceService.findChartInstance(id);
    }

    @Override
    @PostMapping("/update")
    public ResponseResult updateChartInstance(@RequestBody ChartInstance chartInstance) {
        return chartInstanceService.updateChartInstance(chartInstance);
    }

    @Override
    @PostMapping("delete")
    public ResponseResult deleteChartInstance(@PathVariable("id") String id) {
        return chartInstanceService.deleteChartInstance(id);
    }
}

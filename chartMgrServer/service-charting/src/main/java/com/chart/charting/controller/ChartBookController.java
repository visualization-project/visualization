package com.chart.charting.controller;

import com.chart.api.charting.ChartBookControllerApi;
import com.chart.charting.service.ChartBookService;
import com.chart.domain.charting.ChartBook;
import com.chart.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:31
 */
@RestController
@RequestMapping("/chart-book")
public class ChartBookController implements ChartBookControllerApi {

    @Autowired
    private ChartBookService chartBookService;

    @Override
    @GetMapping("/list")
    public ResponseResult findAll() {
        return chartBookService.findAll();
    }

    @Override
    @PostMapping("/add")
    public ResponseResult addChartBook(@RequestBody ChartBook chartBook) {
        return chartBookService.addChartBook( chartBook );
    }

    @Override
    @GetMapping("/find/{id}")
    public ResponseResult findChartBook(@PathVariable("id") String id) {
        return chartBookService.findChartBooks( id );
    }

    @Override
    @PostMapping("/update")
    public ResponseResult updateChartBook(@RequestBody ChartBook chartBook) {
        return chartBookService.updateCharBook( chartBook );
    }

    @Override
    @PostMapping
    public ResponseResult deleteChartBook(@PathVariable("id") String id) {
        return chartBookService.deleteChartBook( id );
    }
}

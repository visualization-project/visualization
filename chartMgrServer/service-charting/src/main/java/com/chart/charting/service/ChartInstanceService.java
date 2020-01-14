package com.chart.charting.service;

import com.chart.charting.dao.ChartInstanceRepository;
import com.chart.domain.charting.ChartInstance;
import com.chart.framework.exception.ExceptionCast;
import com.chart.framework.model.response.CommonCode;
import com.chart.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:34
 */
@Service
public class ChartInstanceService {

    @Autowired
    private ChartInstanceRepository chartInstanceRepository;


    public ResponseResult findAll() {
        List<ChartInstance> instances = chartInstanceRepository.findAll();
        ResponseResult result = new ResponseResult( CommonCode.SUCCESS );
        result.setResult( instances );
        return result;
    }

    public ResponseResult addChartInstance(ChartInstance chartInstance) {
        if (chartInstance ==null || StringUtils.isEmpty(chartInstance.getName())){
            ExceptionCast.cast( CommonCode.INVALID_PARAM );
        }
        Date date = new Date();
        chartInstance.setCreateTime( date );
        chartInstance.setLastUpdate( date );
        chartInstanceRepository.save( chartInstance );
        return new ResponseResult( CommonCode.SUCCESS );
    }

    public ResponseResult findChartInstance(String id) {
        Optional<ChartInstance> instance = chartInstanceRepository.findById( id );
        ResponseResult result = new ResponseResult( CommonCode.SUCCESS );
        result.setResult( instance );
        return result;
    }

    public ResponseResult updateChartInstance(ChartInstance chartInstance) {
        if (chartInstance ==null || StringUtils.isEmpty(chartInstance.getId())){
            ExceptionCast.cast( CommonCode.INVALID_PARAM );
        }
        Date date = new Date();
        chartInstance.setLastUpdate( date );
        return new ResponseResult( CommonCode.SUCCESS );
    }

    public ResponseResult deleteChartInstance(String id) {
        chartInstanceRepository.deleteById( id );
        return new ResponseResult( CommonCode.SUCCESS );
    }
}

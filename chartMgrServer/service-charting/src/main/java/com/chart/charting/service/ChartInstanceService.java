package com.chart.charting.service;

import com.chart.charting.dao.ChartInstanceRepository;
import com.chart.domain.charting.ChartInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:34
 */
@Service
public class ChartInstanceService {

    @Autowired
    private ChartInstanceRepository chartInstanceRepository;


    public List<ChartInstance> findAll() {
        return chartInstanceRepository.findAll();
    }

}

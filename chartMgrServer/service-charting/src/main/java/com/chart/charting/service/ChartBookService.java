package com.chart.charting.service;

import com.chart.charting.dao.ChartBookRepository;
import com.chart.domain.charting.ChartBook;
import com.chart.framework.exception.ExceptionCast;
import com.chart.framework.model.response.CommonCode;
import com.chart.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/28 12:20
 */
@Service
public class ChartBookService {

    @Autowired
    private ChartBookRepository chartBookRepository;

    public List<ChartBook> findAll() {
        return chartBookRepository.findAll();
    }

    /**
     * 添加图册
     *
     * @param chartBook
     * @return
     */
    public ResponseResult addChartBook(ChartBook chartBook) {
        if (chartBook == null ||
                StringUtils.isEmpty( chartBook.getName() )) {
            ExceptionCast.cast( CommonCode.INVALID_PARAM );
        }
        Date date = new Date();
        chartBook.setCreateTime( date );
        chartBook.setLastUpdate( date );
        chartBookRepository.save( chartBook );
        return new ResponseResult( CommonCode.SUCCESS );
    }
}

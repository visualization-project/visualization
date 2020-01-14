package com.chart.charting.service;

import com.chart.charting.dao.ChartBookRepository;
import com.chart.charting.dao.ChartInstanceRepository;
import com.chart.domain.charting.ChartBook;
import com.chart.domain.charting.ChartInstance;
import com.chart.framework.exception.ExceptionCast;
import com.chart.framework.model.response.CommonCode;
import com.chart.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Caiqin
 * @Date: 2019/12/28 12:20
 */
@Service
public class ChartBookService {

    @Autowired
    private ChartBookRepository chartBookRepository;
    @Autowired
    private ChartInstanceRepository chartInstanceRepository;

    public ResponseResult findAll() {
        List<ChartBook> books = chartBookRepository.findAll();
        ResponseResult result = new ResponseResult( CommonCode.SUCCESS );
        result.setResult( books );
        return result;
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

    /**
     * 查询图册详情
     * @param id
     * @return
     */
    public ResponseResult findChartBooks(String id) {
        Optional<ChartBook> chartBook = chartBookRepository.findById( id );
        ChartBook book = chartBook.get();
        ChartInstance instance = new ChartInstance();
        instance.setProjectId( id );
        List<ChartInstance> instanceList = chartInstanceRepository.findAll( Example.of( instance ) );
        ResponseResult result = new ResponseResult( CommonCode.SUCCESS );
        result.setResult( instanceList );
        return result;
    }

    /**
     * 修改图册
     *
     * @param chartBook
     * @return
     */
    public ResponseResult updateCharBook(ChartBook chartBook) {
        if (chartBook == null ||
                StringUtils.isEmpty( chartBook.getId() ) ||
                StringUtils.isEmpty( chartBook.getName() )) {
            ExceptionCast.cast( CommonCode.INVALID_PARAM );
        }
        Date date = new Date();
        chartBook.setLastUpdate( date );
        chartBookRepository.saveAndFlush( chartBook );
        return new ResponseResult( CommonCode.SUCCESS );
    }

    /**
     * 删除图册（修改状态:flagDomain=0 正常；flagDomain=-1 删除；）
     * @param id
     * @return
     */
    public ResponseResult deleteChartBook(String id) {
        Optional<ChartBook> chartBook = chartBookRepository.findById( id );
        ChartBook book = chartBook.get();
        book.setFlagDomain( -1 );
        chartBookRepository.saveAndFlush( book );
        return new ResponseResult( CommonCode.SUCCESS );
    }
}

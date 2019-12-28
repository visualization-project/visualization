package com.chart.api.charting;

import com.chart.domain.charting.ChartBook;
import com.chart.domain.charting.ChartInstance;
import com.chart.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:26
 */
@Api(value = "图册管理接口",description = "提供图册的增删改查")
public interface ChartBookControllerApi {

    public List<ChartBook> findAll();

    @ApiOperation( "添加图册" )
    public ResponseResult addChartBook(ChartBook chartBook);
}

package com.chart.api.charting;

import com.chart.domain.charting.ChartBook;
import com.chart.domain.charting.ChartInstance;
import com.chart.framework.model.response.QueryResponseResult;
import com.chart.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:26
 */
@Api(value = "图表管理接口",description = "提供图表的增删改查")
public interface ChartInstanceControllerApi {

    @ApiOperation( "查询图表" )
    public ResponseResult findAll();

    @ApiOperation( "添加图表" )
    public ResponseResult addChartInstance(ChartInstance chartInstance);

    @ApiOperation( "根据图表ID查询图表" )
    public ResponseResult findChartInstance(String id);

    @ApiOperation( "修改图表" )
    public ResponseResult updateChartInstance(ChartInstance chartInstance);

    @ApiOperation( "删除图表" )
    public ResponseResult deleteChartInstance(String id);
}

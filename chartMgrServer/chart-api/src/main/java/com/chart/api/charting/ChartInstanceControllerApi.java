package com.chart.api.charting;

import com.chart.domain.charting.ChartInstance;
import com.chart.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 17:26
 */
@Api(value = "图表管理接口",description = "提供图表的增删改查")
public interface ChartInstanceControllerApi {

    public List<ChartInstance> findAll();

}

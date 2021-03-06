package com.chart.charting.dao;

import com.chart.domain.charting.ChartInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @Author: Caiqin
 * @Date: 2019/12/27 17:07
 */
@Repository
public interface ChartInstanceRepository extends JpaRepository<ChartInstance,String> {

}

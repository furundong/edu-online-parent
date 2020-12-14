package com.example.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.commonutils.entity.StatisticsDaily;

import java.util.Map;

/**
 * 网站统计日数据(StatisticsDaily)表服务接口
 *
 * @author makejava
 * @since 2020-11-24 08:32:40
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}

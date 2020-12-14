package com.example.statistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commonutils.entity.StatisticsDaily;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网站统计日数据(StatisticsDaily)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-24 08:32:38
 */
@Mapper
public interface StatisticsDailyDao extends BaseMapper<StatisticsDaily> {

}

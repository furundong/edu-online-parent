package com.example.statistics.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.StatisticsDaily;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.statistics.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 网站统计日数据(StatisticsDaily)表控制层
 *
 * @author makejava
 * @since 2020-11-24 08:32:41
 */
@RestController
@RequestMapping("statisticsDaily")
public class StatisticsDailyController {
    /**
     * 服务对象
     */
    @Resource
    private StatisticsDailyService statisticsDailyService;

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin, @PathVariable String end, @PathVariable String type){
        Map<String, Object> map = statisticsDailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }

    @PostMapping("createStatisticsByDate")
    public R createStatisticsByDate(String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param statisticsDaily 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<StatisticsDaily> page, StatisticsDaily statisticsDaily) {
        Page<StatisticsDaily> entity = this.statisticsDailyService.page(page, new QueryWrapper<>(statisticsDaily));
        return R.ok().data(new PageResult<>(entity.getTotal(), entity.getRecords()));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        StatisticsDaily entityById = this.statisticsDailyService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param statisticsDaily 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody StatisticsDaily statisticsDaily) {
        this.statisticsDailyService.save(statisticsDaily);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param statisticsDaily 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody StatisticsDaily statisticsDaily) {
        this.statisticsDailyService.updateById(statisticsDaily);
        return R.ok();
    }

    /**
     * 删除数据单条数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable("id") String id) {
        this.statisticsDailyService.removeById(id);
        return R.ok();
    }

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
    public R delete(@RequestParam("idList") List<String> idList) {
        this.statisticsDailyService.removeByIds(idList);
        return R.ok();
    }
}

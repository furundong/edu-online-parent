package com.example.commonutils.response;

/**
 * create by Freedom on 2020/9/2
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页
 *      {
 *          “success”：“成功”，
 *          “code”：10000
 *          “message”：“ok”，
 *          ”data“：{
 *              total：//总条数
 *              rows ：//数据列表
 *          }
 *      }
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}

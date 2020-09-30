package com.example.commonutils.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by Freedom on 2020/9/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubjectData {

    @ExcelProperty("一级标题")
    private String oneSubjectName;
    @ExcelProperty("二级标题")
    private String twoSubjectName;
}

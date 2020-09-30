package com.example.commonutils.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程科目(EduSubject)表实体类
 *
 * @author makejava
 * @since 2020-09-03 14:46:48
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EduSubject extends Model<EduSubject> {
    @TableId
    //课程类别ID
    private String id;
    //类别名称
    private String title;
    //父ID
    private String parentId;
    //排序字段
    private Integer sort;
    @TableField(fill = FieldFill.INSERT)
    //创建时间
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    //更新时间
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

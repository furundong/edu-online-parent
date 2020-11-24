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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程(EduCourse)表实体类
 *
 * @author makejava
 * @since 2020-09-04 16:08:04
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EduCourse extends Model<EduCourse> {

    public static final String COURSE_DRAFT = "Draft";
    public static final String COURSE_NORMAL = "Normal";
    //课程ID
    @TableId
    private String id;
    //课程讲师ID
    private String teacherId;
    //课程专业ID
    private String subjectId;
    //课程专业父级ID
    private String subjectParentId;
    //课程标题
    private String title;
    //课程销售价格，设置为0则可免费观看
    private BigDecimal price;
    //总课时
    private Integer lessonNum;
    //课程封面图片路径
    private String cover;
    //销售数量
    private Long buyCount;
    //浏览数量
    private Long viewCount;
    //乐观锁
    private Long version;
    //课程状态 Draft未发布  Normal已发布
    private String status;
    //逻辑删除 1（true）已删除， 0（false）未删除
    private Boolean isDeleted;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

package com.example.serviceucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.UcenterMember;
import com.example.commonutils.entity.vo.LoginVo;
import com.example.commonutils.entity.vo.RegisterVo;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.commonutils.utils.JwtUtils;
import com.example.servicebase.exceptionHandler.GuliException;
import com.example.serviceucenter.service.UcenterMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 会员表(UcenterMember)表控制层
 *
 * @author makejava
 * @since 2020-09-25 16:14:42
 */
@RestController
@RequestMapping("ucenter")
public class UcenterMemberController {

    @Resource
    private UcenterMemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data(token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    /**
     * 根据token获取登录信息
     * @param request request
     * @return
     */
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember loginInfoVo = memberService.getLoginVo(memberId);
            return R.ok().data(loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(false,99999,"error");
        }
    }

    /**
     * 服务对象
     */
    @Resource
    private UcenterMemberService ucenterMemberService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param ucenterMember 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UcenterMember> page, UcenterMember ucenterMember) {
        Page<UcenterMember> entity = this.ucenterMemberService.page(page, new QueryWrapper<>(ucenterMember));
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
        UcenterMember entityById = this.ucenterMemberService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param ucenterMember 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UcenterMember ucenterMember) {
        this.ucenterMemberService.save(ucenterMember);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param ucenterMember 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UcenterMember ucenterMember) {
        this.ucenterMemberService.updateById(ucenterMember);
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
        this.ucenterMemberService.removeById(id);
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
        this.ucenterMemberService.removeByIds(idList);
        return R.ok();
    }
}

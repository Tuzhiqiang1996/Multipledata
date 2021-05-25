package com.gch.druidmodule2.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gch.druidmodule2.common.lang.Result;
import com.gch.druidmodule2.entity.TbOrderlist;
import com.gch.druidmodule2.mapper.TbOrderlistMapper;
import com.gch.druidmodule2.service.TbOrderlistService;
import com.gch.druidmodule2.util.JwtUtils2;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tu
 * @since 2021-05-24
 */
@Data
@RestController
@RequestMapping("/tbOrderlist")
public class TbOrderlistController {
    @Autowired
    JwtUtils2 jwtUtils;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TbOrderlistService tbOrderlistService;
    @Resource
    TbOrderlistMapper tbOrderlistMapper;

    @GetMapping("/getorderlist")
    public Result getorderlist(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 10;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbOrderlist> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("id", true);
        iPage = tbOrderlistService.selectPage(iPage, entityWrapper);
        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        int TotalRows = tbOrderlistService.countAll();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ(iPage);
    }
    @PostMapping
    public Result addorder(){

        return Result.succ("");
    }
}


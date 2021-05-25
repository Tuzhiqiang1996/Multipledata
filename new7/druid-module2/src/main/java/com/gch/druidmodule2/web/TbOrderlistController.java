package com.gch.druidmodule2.web;


import com.gch.druidmodule2.common.lang.Result;
import com.gch.druidmodule2.service.TbOrderlistService;
import com.gch.druidmodule2.util.JwtUtils2;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getorderlist")
    public Result getorderlist() {
        return Result.succ("66");
    }
}


package com.gch.druidmodule2.web;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gch.druidmodule2.common.lang.Result;
import com.gch.druidmodule2.entity.MUser;
import com.gch.druidmodule2.mapper.MUserMapper2;
import com.gch.druidmodule2.service.MUserService2;
import com.gch.druidmodule2.util.JwtUtils2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Data
//@Controller
@RestController
@RequestMapping("/userio")
public class MUserController2 {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    MUserService2 mUserService;
    @Autowired
    JwtUtils2 jwtUtils;
    @Resource
    MUserMapper2 mUserMapper2;
    DateTime dt = DateTime.now();


    /**
     * [java.lang.Integer, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/8 13:40
     * @message 完成ok
     * pagenum  第几页
     * pagerow  每页的数量
     */
    @GetMapping("userList2")
    public Result getuserlist2(Integer currentPage) {
        if (currentPage == null || currentPage < 0) {
            currentPage = 1;
        }
        Integer pagerow = 8;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<MUser> entityWrapper = new EntityWrapper<>();
        iPage = mUserService.selectPage(iPage, entityWrapper);

        if (iPage == null ) {
            return Result.fail("没有数据！");
        }
        JSONObject jsonObject = new JSONObject(iPage);
        JSONArray jsonArray = (JSONArray) jsonObject.get("records");
//        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonData = (JSONObject) jsonArray.get(i);//得到对象中的第i条记录
            jsonData.remove("password");
//            System.out.println("data[" + i + "]:" + jsonData.remove("password"));
        }
        return Result.succ("获取成功！", jsonObject);
    }

    @GetMapping("/getlisttu")
    public Result getlist(Integer currentPage) {
        if (currentPage == null || currentPage < 0) {
            currentPage = 1;
        }
        Page iPage = new Page<>(currentPage, 8);
//        查询不出结果 0  第一种  可以查询 依赖要导入到本模块中
        EntityWrapper<MUser> entityWrapper = new EntityWrapper<>();
        iPage = mUserService.selectPage(iPage, entityWrapper);
//        第二种  查出总数 分页信息 只能查询自定义的区间范围
//        EntityWrapper<MUser> entityWrapper = new EntityWrapper<>();
//        List<MUser> mUsers = mUserMapper2.selectPage(iPage,entityWrapper);
//        第三种 分页   查询条件 ？
//        PageHelper.startPage(currentPage, 8);
//        List<MUser> list = mUserService.findAllbyPage(currentPage,8);
//        PageInfo<MUser> pageInfo = new PageInfo<>(list);
        return Result.succ(iPage);
    }

}

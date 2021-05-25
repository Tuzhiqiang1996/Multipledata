package com.example.druidmodule1.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.druidmodule1.common.Page.PageList;
import com.example.druidmodule1.common.lang.Result;
import com.example.druidmodule1.entity.TbDeviceLog;
import com.example.druidmodule1.service.TbDeviceLogService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@RestController
public class TbDeviceLogController {
    @Autowired
    TbDeviceLogService deviceLogService;
    @Resource
    private JdbcTemplate jdbcTemplate;

//    @GetMapping("/devlogList")
//    public Result logiest(Integer currentPage) {
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 100;
//        PageList pageList = new PageList();
//        List<TbDeviceLog> data = deviceLogService.findAllbyPage(currentPage, pagerow);
//        int TotalRows = deviceLogService.countAll();
//        pageList.setPage(currentPage);
//        pageList.setSize(pagerow);
//        pageList.setTotal(TotalRows);
//        int pages = 0;
//        if (TotalRows % pagerow == 0) {
//            pages = TotalRows / pagerow;
//        } else {
//            pages = TotalRows / pagerow + 1;
//        }
//                System.out.println("目前分页的总页数是"+pages);
//        pageList.setPages(pages);
//
//        pageList.setRecords(data);
//
//        return Result.succ("操作成功！", pageList);
//    }


//    @GetMapping("/searchlog")
//    public Result searchData(String deviceid, Integer currentPage, String starttime, String endtime) {
//
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 100;
//        PageList pageList = new PageList();
//        List<TbDeviceLog> data = deviceLogService.findAllbyPage(currentPage, pagerow, deviceid, starttime, endtime);
//        //        int TotalRows = deviceLogService.countAll();
//        List<TbDeviceLog> datasize = deviceLogService.findAllbyPagenum(currentPage, pagerow, deviceid, starttime, endtime);
//        int TotalRows = datasize.size();
//        pageList.setPage(currentPage);
//        pageList.setSize(pagerow);
//        pageList.setTotal(TotalRows);
//        int pages = 0;
//        if (TotalRows % pagerow == 0) {
//            pages = TotalRows / pagerow;
//        } else {
//            pages = TotalRows / pagerow + 1;
//        }
//        pageList.setPages(pages);
//
//        pageList.setRecords(data);
//        return Result.succ("", pageList);
//    }


    /**
     * [java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/13 16:23
     * @message 实验分析 上诉查询方法 比之下的方法慢
     */


    @GetMapping("/devlogList")
    public Result logiest(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceLog> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("id", true);
        iPage = deviceLogService.selectPage(iPage, entityWrapper);

        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        int TotalRows = deviceLogService.countAll();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("获取成功！", iPage);
    }

    /**
     * [java.lang.String, java.lang.Integer, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/13 16:24
     * @message
     */
    @GetMapping("/searchlog")
    public Result searchData(String deviceid, Integer currentPage, String starttime, String endtime) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceLog> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("addtime", true);
        if (deviceid != null && deviceid.length() != 0) {
            entityWrapper.like("deviceid", deviceid);
        }
        if (starttime != null && starttime.length() != 0) {
            entityWrapper.between("addtime", starttime, endtime);
        }

        iPage = deviceLogService.selectPage(iPage, entityWrapper);

        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        List<TbDeviceLog> datasize = deviceLogService.findAllbyPagenum(currentPage, pagerow, deviceid, starttime, endtime);
        int TotalRows = datasize.size();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("获取成功！", iPage);
    }
}


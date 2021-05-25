package com.example.druidmodule1.web;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.druidmodule1.common.lang.Result;
import com.example.druidmodule1.entity.TbDeviceListXiaojiang;
import com.example.druidmodule1.mapper.TbDeviceListXiaojiangMapper;
import com.example.druidmodule1.service.TbDeviceListXiaojiangService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RestController
@Controller
@RequestMapping("/")
public class TbDeviceListXiaojiangController {
    @Autowired
    TbDeviceListXiaojiangService xiaoJListService;
    @Resource
    JdbcTemplate jdbcTemplate;
    @Resource
    TbDeviceListXiaojiangMapper tbDeviceListXiaojiangMapper;
    DateTime dt = DateTime.now();

//        @GetMapping("/xiaojlist")
//    public Result xiaojlist(Integer currentPage) {
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 100;
//        PageList pageList = new PageList();
//        List<TbDeviceListXiaojiang> data = xiaoJListService.findAllbyPage(currentPage, pagerow);
//        int TotalRows = xiaoJListService.countAll();
//        pageList.setPage(currentPage);
//        pageList.setSize(pagerow);
//        pageList.setTotal(TotalRows);
//        int pages = 0;
//        if (TotalRows % pagerow == 0) {
//            pages = TotalRows / pagerow;
//        } else {
//            pages = TotalRows / pagerow + 1;
//        }
////        System.out.println("目前分页的总页数是"+pages);
//        pageList.setPages(pages);
//
//        pageList.setRecords(data);
//        return Result.succ("操作成功！", pageList);
//    }

    @GetMapping("/xiaojlist")
    public Result xiaojlist(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceListXiaojiang> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("test_datetime", true);
        iPage = xiaoJListService.selectPage(iPage, entityWrapper);
        if (iPage == null) {
            return Result.fail("没有数据！");
        }

        int TotalRows = xiaoJListService.countAll();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("操作成功！", iPage);
    }

    /**
     * [java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/10 14:57
     * @message 数量查询
     */
    /**
     @GetMapping("/xjtatistical6") public Result statisticalNum6(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
     String sql;
     Long userList;
     Integer d = 3;
     if (num == null) {
     return Result.fail("null");
     }
     if (num >= d) {
     if (orderId != null && orderId.length() != 0) {
     if (starttime != null && starttime.length() != 0) {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count >=? AND order_id=? AND test_datetime BETWEEN ? AND ? ";
     userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
     } else {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count >=? AND order_id=? ";
     userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId));
     }

     } else {
     if (starttime != null && starttime.length() != 0) {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count >=?  AND test_datetime BETWEEN ? AND ? ";
     userList = (jdbcTemplate.queryForObject(sql, Long.class, num, starttime, endtime));
     } else {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count >=? ";
     userList = jdbcTemplate.queryForObject(sql, Long.class, num);
     }
     }

     } else {
     if (orderId != null && orderId.length() != 0) {
     if (starttime != null && starttime.length() != 0) {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count =? AND order_id=? AND test_datetime BETWEEN ? AND ? ";
     userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
     } else {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count =? AND order_id=? ";
     userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId));
     }

     } else {
     if (starttime != null && starttime.length() != 0) {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count =? AND test_datetime BETWEEN ? AND ? ";
     userList = (jdbcTemplate.queryForObject(sql, Long.class, num, starttime, endtime));
     } else {
     sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count =? ";
     userList = jdbcTemplate.queryForObject(sql, Long.class, num);
     }
     }
     }
     return Result.succ("操作成功", userList);
     }
     */

    /**
     * [java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/11 12:25
     * @message 与上诉方法相同
     */


    @GetMapping("/xjtatistical")
    public Result statisticalNum(Integer num, Integer currentPage, String orderId, String starttime, String endtime,String deviceid) {
        EntityWrapper<TbDeviceListXiaojiang> entityWrapper = new EntityWrapper();
        if (orderId != null && orderId.length() != 0) {
            entityWrapper.like("order_id", orderId);
        }
        if (deviceid != null && deviceid.length() != 0) {
            entityWrapper.like("deviceid", deviceid);
        }
        // 获取列表是没有做时间处理 这里也是同样
        if (starttime != null && starttime.length() != 0) {
            entityWrapper.between("test_datetime", starttime, endtime);
        }
        Integer d = 3;
        if (num != null) {
            if (num >= d) {
                entityWrapper.ge("check_count", num);
            } else {

                entityWrapper.eq("check_count", num);
            }
        }


        List<TbDeviceListXiaojiang> devLists = tbDeviceListXiaojiangMapper.selectList(entityWrapper);

        return Result.succ("操作成功", devLists.size());
    }

    /**
     * [java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/10 16:05
     * @message 分页查询 旧方法
     */

//    @GetMapping("/searchXjlist")
//    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 100;
//        PageList pageList = new PageList();
//        List<TbDeviceListXiaojiang> data = xiaoJListService.searchAllbyPage(currentPage, pagerow, deviceid, currentPage, orderId, starttime, endtime, sn);
//        List<TbDeviceListXiaojiang> datasize = xiaoJListService.searchAllbyPagenum(currentPage, pagerow, deviceid, currentPage, orderId, starttime, endtime, sn);
//
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
////        System.out.println("目前分页的总页数是"+pages);
//        pageList.setPages(pages);
//
//        pageList.setRecords(data);
//
//
//        return Result.succ("操作成功", pageList);
//    }


    /**
     * [java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/13 16:35
     * @message 分页  新
     */


    @GetMapping("/searchXjlist")
    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceListXiaojiang> queryWrapper = new EntityWrapper<>();
        queryWrapper.orderBy("test_datetime", true);

        if (deviceid != null && deviceid.length() != 0) {
            queryWrapper.like("deviceid", deviceid);
        }
        if (orderId != null && orderId.length() != 0) {
            queryWrapper.like("order_id", orderId);
        }
        if (sn != null && sn.length() != 0) {
            queryWrapper.like("sn", sn);
        }
        if (starttime != null && starttime.length() != 0) {
            queryWrapper.between("test_datetime", starttime, endtime);
        }
        iPage = xiaoJListService.selectPage(iPage, queryWrapper);
        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        List<TbDeviceListXiaojiang> datasize = xiaoJListService.searchAllbyPagenum(currentPage, pagerow, deviceid, currentPage, orderId, starttime, endtime, sn);
        int TotalRows = datasize.size();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("操作成功！", iPage);
    }


    @PostMapping("/listFix")
    public Result listFix(@Validated @RequestBody TbDeviceListXiaojiang xiaoJList) {
        TbDeviceListXiaojiang xiaoJList1 = xiaoJListService.selectById(xiaoJList.getId());

        xiaoJList1.setDeviceid(xiaoJList.getDeviceid());
        xiaoJList1.setAddr1(xiaoJList.getAddr1());
        xiaoJList1.setAddr2(xiaoJList.getAddr2());
        xiaoJList1.setCreateTime(xiaoJList.getCreateTime());
        xiaoJList1.setTestResult(xiaoJList.getTestResult());
        xiaoJList1.setTestDatetime(xiaoJList.getTestDatetime());
        xiaoJList1.setPackageDatetime(xiaoJList.getPackageDatetime());
        xiaoJList1.setPackages(xiaoJList.getPackages());
        xiaoJList1.setCheckDatetime(xiaoJList.getCheckDatetime());
        xiaoJList1.setCheckCount(xiaoJList.getCheckCount());
        xiaoJListService.insertOrUpdate(xiaoJList1);
        return Result.succ("修改成功！");
    }

    @PostMapping("/xjlist")
    public Result xjlist(@RequestBody List<TbDeviceListXiaojiang> xjlists) {
        if (xjlists.size() == 0 || xjlists == null) {
            return Result.fail("插入数据为空！");
        }
        TbDeviceListXiaojiang savefile = new TbDeviceListXiaojiang();
        List<TbDeviceListXiaojiang> savefileList = new ArrayList<>(xjlists);

        /**
         * 第二种时间明显减少
         * 1000/78ms
         * 1000每次
         */
        xiaoJListService.insertBatch(savefileList);
        return Result.succ("插入成功！", savefile);
    }
}


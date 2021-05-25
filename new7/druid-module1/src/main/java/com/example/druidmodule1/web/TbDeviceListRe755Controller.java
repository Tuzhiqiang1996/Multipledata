package com.example.druidmodule1.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.druidmodule1.common.lang.Result;
import com.example.druidmodule1.entity.TbDeviceListRe755;
import com.example.druidmodule1.mapper.TbDeviceListRe755Mapper;
import com.example.druidmodule1.service.TbDeviceListRe755Service;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
@RequestMapping("/")
public class TbDeviceListRe755Controller {
    @Autowired
    TbDeviceListRe755Service re755Service;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private TbDeviceListRe755Mapper tbDeviceListRe755Mapper;

//    @GetMapping("/TYlist")
//    public Result xiaojlist(Integer currentPage) {
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 100;
//        PageList pageList = new PageList();
//        List<TbDeviceListRe755> data = re755Service.findAllbyPage(currentPage, pagerow);
//        int TotalRows = re755Service.countAll();
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

    /**
     * [java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/13 16:45
     * @message 更新上诉方法  加速查询
     */

    @GetMapping("/TYlist")
    public Result xiaojlist(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceListRe755> queryWrapper = new EntityWrapper<>();
        queryWrapper.orderBy("test_datetime", true);
        iPage = re755Service.selectPage(iPage, queryWrapper);
        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        int TotalRows = re755Service.countAll();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("操作成功！", iPage);
    }

//    @GetMapping("/searchTYlist")
//    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {
//
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 100;
//        PageList pageList = new PageList();
//        List<TbDeviceListRe755> data = re755Service.searchAllbyPage(currentPage, pagerow, deviceid, currentPage, orderId, starttime, endtime, sn);
//        List<TbDeviceListRe755> datasize = re755Service.searchAllbyPagenum(currentPage, pagerow, deviceid, currentPage, orderId, starttime, endtime, sn);
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
//        pageList.setPages(pages);
//
//        pageList.setRecords(data);
//        return Result.succ("操作成功", pageList);
//    }

    @GetMapping("/searchTYlist")
    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceListRe755> queryWrapper = new EntityWrapper<>();
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

        iPage = re755Service.selectPage(iPage, queryWrapper);
        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        List<TbDeviceListRe755> datasize = re755Service.searchAllbyPagenum(currentPage, pagerow, deviceid, currentPage, orderId, starttime, endtime, sn);
        int TotalRows = datasize.size();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("操作成功", iPage);
    }

    @GetMapping("/tytatistical6")
    public Result statisticalNum6(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        String sql;
        Long userList;
        Integer d = 3;
        if (num == null) {
            return Result.fail("null");
        }
        if (num >= d) {
            if (orderId != null && orderId.length() != 0) {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count >=? AND order_id=? AND test_datetime BETWEEN ? AND ? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count >=? AND order_id=? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId));
                }

            } else {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count >=?  AND test_datetime BETWEEN ? AND ? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count >=? ";
                    userList = jdbcTemplate.queryForObject(sql, Long.class, num);
                }
            }

        } else {
            if (orderId != null && orderId.length() != 0) {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count =? AND order_id=? AND test_datetime BETWEEN ? AND ? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count =? AND order_id=? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId));
                }

            } else {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count =? AND test_datetime BETWEEN ? AND ? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re755 WHERE check_count =? ";
                    userList = jdbcTemplate.queryForObject(sql, Long.class, num);
                }
            }
        }
        return Result.succ("操作成功", userList);
    }

    /**
     * [java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/11 14:49
     * @message 功能同上
     */
    @GetMapping("/tytatistical")
    public Result statisticalNum(Integer num, Integer currentPage, String orderId, String starttime, String endtime, String deviceid) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        EntityWrapper<TbDeviceListRe755> entityWrapper = new EntityWrapper();
        if (orderId != null && orderId.length() != 0) {
            entityWrapper.like("order_id", orderId);
        }
        if (deviceid != null && deviceid.length() != 0) {
            entityWrapper.like("deviceid", deviceid);
        }
        Integer d = 3;
        if (num != null) {
            if (num >= d) {
                entityWrapper.ge("check_count", num);
            } else {

                entityWrapper.eq("check_count", num);
            }
        }
// 获取列表是没有做时间处理 这里也是同样
        if (starttime != null && starttime.length() != 0) {
            entityWrapper.between("test_datetime", starttime, endtime);
        }
        List<TbDeviceListRe755> devLists = tbDeviceListRe755Mapper.selectList(entityWrapper);
        return Result.succ("操作成功", devLists.size());
    }

    @PostMapping("/tyreceivefile")
    public Result tyreceivefile(@RequestBody List<TbDeviceListRe755> savefiles) {
        if (savefiles.size() == 0 || savefiles == null) {
            return Result.fail("插入数据为空！");
        }
        TbDeviceListRe755 savefile = new TbDeviceListRe755();
        List<TbDeviceListRe755> savefileList = new ArrayList<>(savefiles);
        /**
         * 第一种时间长
         * 1000/1min
         * 1000每次
         */
//        for (int i = 0; i < savefileList.size(); i++) {
//            BeanUtil.copyProperties((savefiles.get(i)), savefile);
//            tuYListService.saveOrUpdate(savefile);
//        }
        /**
         * 第二种时间明显减少
         * 1000/78ms
         * 1000每次
         */
        re755Service.insertBatch(savefileList);
        return Result.succ("插入成功！", savefile);
    }
}


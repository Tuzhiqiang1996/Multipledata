package com.example.druidmodule1.web;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.druidmodule1.common.lang.Result;
import com.example.druidmodule1.entity.TbOrder;
import com.example.druidmodule1.mapper.TbOrderMapper;
import com.example.druidmodule1.service.TbOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 * 设备订单
 */
@Data
@RestController
@RequestMapping("/")
public class TbOrderController {
    @Autowired
    TbOrderService orderService;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private TbOrderMapper tbOrderMapper;

    //    @GetMapping("/order")
//    public Result order(Integer currentPage) {
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Integer pagerow = 8;
//        PageList pageList = new PageList();
//        List<TbOrder> data = orderService.findAllbyPage(currentPage, pagerow);
//        int TotalRows = orderService.countAll();
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
//        return Result.succ("获取成功", pageList);
//    }
    @GetMapping("/order")
    public Result order(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 10;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbOrder> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("id", true);
        iPage = orderService.selectPage(iPage, entityWrapper);

        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        int TotalRows = orderService.countAll();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("获取成功", iPage);
    }

    /**
     * [com.example.druidmodule1.entity.TbOrder]
     *
     * @return com.example.druidmodule1.common.lang.Result
     * @author Tu
     * @date 2021/5/24 14:23
     * @message 新增
     */

    @PostMapping("/orderadd")
    public Result orderadd(@RequestBody TbOrder orderList) {

        TbOrder ord = new TbOrder();

        BeanUtil.copyProperties(orderList, ord);
        System.out.println(orderList);
        orderService.insertOrUpdate(orderList);
        return Result.succ("新增成功！");
    }

    /**
     * [com.example.druidmodule1.entity.TbOrder]
     *
     * @return com.example.druidmodule1.common.lang.Result
     * @author Tu
     * @date 2021/5/24 14:22
     * @message 修改
     */
    @PostMapping("/editorder")
    public Result editorder(@RequestBody TbOrder orderList) {
        TbOrder ord = new TbOrder();

        BeanUtil.copyProperties(orderList, ord);
        orderService.insertOrUpdate(orderList);
        return Result.succ("操作成功！");
    }

    @GetMapping("/deleteorder")
    public Result deleteord(@RequestParam Integer id, @RequestParam Integer status) {
        if (status != 0) {
            return Result.fail("没有权限");
        }


        orderService.deleteById(id);

        return Result.succ("删除成功！");
    }

    /**
     * [java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/11 10:45
     * @message 查询类似的
     * https://www.cnblogs.com/jayhou/p/9824127.html
     * 通过tbOrderMapper、selectPage 来查询 分页还是要自己封装
     * 这里只是查询到 数据的全部
     */
    @GetMapping("getlistnumber")
    public Result getlistnumber(String orderNumber) {

        EntityWrapper<TbOrder> entityWrapper = new EntityWrapper();
        entityWrapper.like("order_number", orderNumber);
        List<TbOrder> userList = tbOrderMapper.selectPage(new Page<TbOrder>(1, 1), entityWrapper);
        //判断数据是否为空
        if (userList == null || userList.size() == 0) {
            return Result.fail("没有数据！");
        }
        return Result.succ("查询成功！", userList);
    }

    @PostMapping("/saveorderfile")
    public Result saveorder(@RequestBody TbOrder setfile) {
        TbOrder s = new TbOrder();
        BeanUtil.copyProperties(setfile, s);
        orderService.insertOrUpdate(s);
        return Result.succ("插入数据成功！", s);
    }

    /**
     * [java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/10 19:14
     * @message EntityWrapper  jdk1.7
     * qQueryWrapper 1.8
     */


    @GetMapping("/queryorderlist")
    public Result queryorderlist(String projectName, String orderNumber, Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 10;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbOrder> entityWrapper = new EntityWrapper<>();
        if (projectName != null && projectName.length() != 0) {
            entityWrapper.like("project_name", projectName);
        }
        if (orderNumber != null && orderNumber.length() != 0) {
            entityWrapper.like("order_number", orderNumber);
        }
        iPage = orderService.selectPage(iPage, entityWrapper);
        if (iPage == null) {
            return Result.fail("没有数据！");
        }
        List<TbOrder> datasize = orderService.findAllbyPagenum(projectName, orderNumber, currentPage);
        int TotalRows = datasize.size();
        if (iPage.getTotal() == 0) {
            iPage.setTotal(TotalRows);
        }
        return Result.succ("操作成功", iPage);
    }
}


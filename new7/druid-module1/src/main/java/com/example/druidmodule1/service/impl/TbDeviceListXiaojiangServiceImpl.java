package com.example.druidmodule1.service.impl;

import com.example.druidmodule1.mapper.TbDeviceListXiaojiangMapper;
import com.example.druidmodule1.entity.TbDeviceListXiaojiang;
import com.example.druidmodule1.service.TbDeviceListXiaojiangService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Service
public class TbDeviceListXiaojiangServiceImpl extends ServiceImpl<TbDeviceListXiaojiangMapper, TbDeviceListXiaojiang> implements TbDeviceListXiaojiangService {
    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_device_list_xiaojiang";
        List<TbDeviceListXiaojiang> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListXiaojiang.class));
        return userList.size();
    }

    @Override
    public List<TbDeviceListXiaojiang> findAllbyPage(Integer currentPage, Integer pagerow) {

        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_xiaojiang  limit " + starter + " , " + pagerow;
        List<TbDeviceListXiaojiang> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListXiaojiang.class));

        return list;
    }

    @Override
    public int findcheckcount(Integer pagerow, Integer num, Integer currentPage, String orderId, String starttime, String endtime) {

//        String sql = "select COUNT(*) from tb_device_list_xiaojiang WHERE check_count=1  ";
//        int list = jdbcTemplate.queryForObject(sql, TbDeviceListXiaojiang.class);
//        System.out.println(list);
//        return list;
        return 1;
    }

    @Override
    public List<TbDeviceListXiaojiang> searchAllbyPage(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_xiaojiang WHERE ";
        String limit = " limit " + starter + " , " + pagerow;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = " order_id  LIKE \"%" + orderId + "%\"";
        String snnum = " sn  LIKE \"%" + sn + "%\"";
        String time = " test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            sql+=devid;
        }
        if (orderId != null && orderId.length() != 0) {
            sql+=ordid;
        }
        if (sn != null && sn.length() != 0) {
            sql+=snnum;
        }
        if (starttime != null && starttime.length() != 0) {
            sql+=time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql+=ordid + " and " + devid;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql+=devid + " and " + time;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql+=ordid + " and " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql+=ordid + " and " + devid + " and " + time;
        }
        sql+=limit;
//        List<TbDeviceListXiaojiang> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListXiaojiang.class));
System.out.println(sql);
        return null;
    }

    @Override
    public List<TbDeviceListXiaojiang> searchAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn) {

        String sql = "select * from tb_device_list_xiaojiang WHERE  ";
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String snnum = "sn  LIKE \"%" + sn + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + devid;
//            sql+=devid;
        }
        if (orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + ordid;
//            sql+=ordid;
        }
        if (sn != null && sn.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + snnum;
//            sql+=snnum;
        }
        if (starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + ordid + " and " + devid;
//            sql+=ordid + " and " + devid;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + devid + " and " + time;
//            sql+= devid + " and " + time;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + ordid + " and " + time;
//            sql+= ordid + " and " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_xiaojiang WHERE " + ordid + " and " + devid + " and " + time;
//            sql+= ordid + " and " + devid + " and " + time;
        }
        List<TbDeviceListXiaojiang> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListXiaojiang.class));

        return list;
    }


}

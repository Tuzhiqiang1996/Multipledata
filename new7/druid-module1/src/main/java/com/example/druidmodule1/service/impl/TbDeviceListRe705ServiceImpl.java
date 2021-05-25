package com.example.druidmodule1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.druidmodule1.entity.TbDeviceListRe705;
import com.example.druidmodule1.mapper.TbDeviceListRe705Mapper;
import com.example.druidmodule1.service.TbDeviceListRe705Service;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TbDeviceListRe705ServiceImpl extends ServiceImpl<TbDeviceListRe705Mapper, TbDeviceListRe705> implements TbDeviceListRe705Service {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TbDeviceListRe705> findAllbyPage(Integer currentPage, Integer pagerow) {


        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_re705  limit " + starter + " , " + pagerow;
        List<TbDeviceListRe705> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_device_list_re705";
        List<TbDeviceListRe705> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));
        return userList.size();
    }

    @Override
    public List<TbDeviceListRe705> searchAllbyPage(Integer pagerow, String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_re705 WHERE ";
        String limit = " limit " + starter + " , " + pagerow;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String snuid = " sn  LIKE \"%" + sn + "%\"";
        String time = " test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endtime1 = ft.format(dNow);
        String starttime1 = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
        if (deviceid != null && deviceid.length() != 0) {
            sql += devid;
        }
        if (orderId != null && orderId.length() != 0) {
            sql += ordid;
        }
        if (sn != null && sn.length() != 0) {
            sql += snuid;
        }
        if (starttime != null && starttime.length() != 0) {
            sql += time;
        }

        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql += ordid + " and " + devid;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql += devid + " and " + time;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql += ordid + " and " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql += ordid + " and " + devid + " and " + time;
        }
        sql += limit;
//        System.out.println(sql);
        List<TbDeviceListRe705> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));

        return list;
    }

    @Override
    public List<TbDeviceListRe705> searchAllbyPagenum(String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        String sql = "select * from tb_device_list_re705 WHERE ";
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String snuid = " sn  LIKE \"%" + sn + "%\"";
        String time = " test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endtime1 = ft.format(dNow);
        String starttime1 = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
        if (deviceid != null && deviceid.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + devid;
//            sql += devid;
        }
        if (orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid;
//            sql += ordid;
        }
        if (sn != null && sn.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + snuid;
//            sql += snuid;
        }
        if (starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + time;
//            sql += time;
        }

        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
          String  sql1= ordid + " and " + devid;
            sql = "select * from tb_device_list_re705 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            String  sql1= devid + " and " + time;
            sql = "select * from tb_device_list_re705 WHERE " + sql1;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            String  sql1= ordid + " and " + time;
            sql = "select * from tb_device_list_re705 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            String  sql1= ordid + " and " + devid + " and " + time;
            sql = "select * from tb_device_list_re705 WHERE " + sql1;
        }
        System.out.println(sql);
        List<TbDeviceListRe705> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));

        return list;
    }
}

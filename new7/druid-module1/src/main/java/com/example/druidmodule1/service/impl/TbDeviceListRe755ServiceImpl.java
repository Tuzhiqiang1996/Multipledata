package com.example.druidmodule1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.druidmodule1.entity.TbDeviceListRe755;
import com.example.druidmodule1.mapper.TbDeviceListRe755Mapper;
import com.example.druidmodule1.service.TbDeviceListRe755Service;
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
public class TbDeviceListRe755ServiceImpl extends ServiceImpl<TbDeviceListRe755Mapper, TbDeviceListRe755> implements TbDeviceListRe755Service {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TbDeviceListRe755> findAllbyPage(Integer currentPage, Integer pagerow) {
        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_re755  limit " + starter + " , " + pagerow;
        List<TbDeviceListRe755> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_device_list_re755";
        List<TbDeviceListRe755> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));
        return userList.size();
    }

    @Override
    public List<TbDeviceListRe755> searchAllbyPage(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_re755 WHERE ";
        String limit = " limit " + starter + " , " + pagerow;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String snuid = "sn  LIKE \"%" + sn + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            String sql1 = devid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (orderId != null && orderId.length() != 0) {

            String sql1 = ordid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (starttime != null && starttime.length() != 0) {

            String sql1 = time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (sn != null && sn.length() != 0) {

            String sql1 = snuid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {

            String sql1 = ordid + " and " + devid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {

            String sql1 = ordid + " and " + devid + " and " + time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {

            String sql1 =  devid + " and " + time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {

            String sql1 =  ordid + " and " + time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        sql += limit;
        List<TbDeviceListRe755> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));

        return list;
    }

    @Override
    public List<TbDeviceListRe755> searchAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn) {
        String sql = "select * from tb_device_list_re755 WHERE ";
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String snuid = "sn  LIKE \"%" + sn + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            String sql1 = devid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (orderId != null && orderId.length() != 0) {

            String sql1 = ordid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (starttime != null && starttime.length() != 0) {

            String sql1 = time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (sn != null && sn.length() != 0) {

            String sql1 = snuid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {

            String sql1 = ordid + " and " + devid;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {

            String sql1 =  devid + " and " + time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {

            String sql1 =  ordid + " and " + time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            String sql1 = ordid + " and " + devid + " and " + time;
            sql = "select * from tb_device_list_re755 WHERE " + sql1;
        }

        List<TbDeviceListRe755> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));

        return list;
    }
}

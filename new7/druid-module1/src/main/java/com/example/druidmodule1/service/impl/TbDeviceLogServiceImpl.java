package com.example.druidmodule1.service.impl;

import com.example.druidmodule1.mapper.TbDeviceLogMapper;
import com.example.druidmodule1.entity.TbDeviceLog;
import com.example.druidmodule1.service.TbDeviceLogService;
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
public class TbDeviceLogServiceImpl extends ServiceImpl<TbDeviceLogMapper, TbDeviceLog> implements TbDeviceLogService {
    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_device_log";
        List<TbDeviceLog> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceLog.class));
        return userList.size();
    }

    @Override
    public List<TbDeviceLog> findAllbyPage(Integer currentPage, Integer pagerow) {

        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_log  limit " + starter + " , " + pagerow;
        List<TbDeviceLog> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceLog.class));

        return list;
    }

    @Override
    public List<TbDeviceLog> findAllbyPage(Integer currentPage, Integer pagerow, String deviceid, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        int starter = (currentPage - 1) * pagerow;
        String sql1 = "select * from tb_device_log  limit " + starter + " , " + pagerow;
        if (deviceid != null && deviceid.length() != 0) {
            String sql = "select * from tb_device_log WHERE deviceid LIKE %" + deviceid + "% limit " + starter + " , " + pagerow;
            String sql2 = "select * from tb_device_log WHERE deviceid = " + deviceid;
            sql2 = "select * from tb_device_log WHERE deviceid  LIKE %\"" + deviceid + "%\"";
            sql1 = "select * from tb_device_log WHERE deviceid  LIKE \"%" + deviceid + "%\"" + " limit " + starter + " , " + pagerow;

        }
        if (starttime != null && starttime.length() != 0) {
            sql1 = "select * from tb_device_log WHERE addtime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"" + " limit " + starter + " , " + pagerow;
        }
        if (starttime != null && starttime.length() != 0 && deviceid != null && deviceid.length() != 0) {
            sql1 = "select * from tb_device_log WHERE deviceid  LIKE \"%" + deviceid + "%\"" + " and addtime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"" + " limit " + starter + " , " + pagerow;
        }


        List<TbDeviceLog> list = jdbcTemplate.query(sql1, new BeanPropertyRowMapper<>(TbDeviceLog.class));

        return list;
    }

    @Override
    public List<TbDeviceLog> findAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        int starter = (currentPage - 1) * pagerow;
        String sql1 = "select * from tb_device_log  limit " + starter + " , " + pagerow;
        if (deviceid != null && deviceid.length() != 0) {
            String sql = "select * from tb_device_log WHERE deviceid LIKE %" + deviceid + "% limit " + starter + " , " + pagerow;
            String sql2 = "select * from tb_device_log WHERE deviceid = " + deviceid;
            sql2 = "select * from tb_device_log WHERE deviceid  LIKE %\"" + deviceid + "%\"";
            sql1 = "select * from tb_device_log WHERE deviceid  LIKE \"%" + deviceid + "%\"";//+ " limit " + starter + " , " + pagerow;

        }
        if (starttime != null && starttime.length() != 0) {
            sql1 = "select * from tb_device_log WHERE addtime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        }
        if (starttime != null && starttime.length() != 0 && deviceid != null && deviceid.length() != 0) {
            sql1 = "select * from tb_device_log WHERE deviceid  LIKE \"%" + deviceid + "%\"" + " and addtime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        }


        List<TbDeviceLog> list = jdbcTemplate.query(sql1, new BeanPropertyRowMapper<>(TbDeviceLog.class));

        return list;
    }

}

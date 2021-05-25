package com.example.druidmodule1.service;

import com.example.druidmodule1.entity.TbDeviceLog;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
public interface TbDeviceLogService extends IService<TbDeviceLog> {


    int countAll();

    List<TbDeviceLog> findAllbyPage(Integer currentPage, Integer pagerow);

    List<TbDeviceLog> findAllbyPage(Integer currentPage, Integer pagerow, String deviceid, String starttime, String endtime);

    List<TbDeviceLog> findAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, String starttime, String endtime);
}

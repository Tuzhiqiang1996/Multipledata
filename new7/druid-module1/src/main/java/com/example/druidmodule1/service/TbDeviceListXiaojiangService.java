package com.example.druidmodule1.service;

import com.example.druidmodule1.entity.TbDeviceListXiaojiang;
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
public interface TbDeviceListXiaojiangService extends IService<TbDeviceListXiaojiang> {

    int countAll();

    List<TbDeviceListXiaojiang> findAllbyPage(Integer currentPage, Integer pagerow);

     int findcheckcount(Integer pagerow , Integer num, Integer currentPage, String orderId, String starttime, String endtime);

    List<TbDeviceListXiaojiang> searchAllbyPage(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn);

    List<TbDeviceListXiaojiang> searchAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn);

}

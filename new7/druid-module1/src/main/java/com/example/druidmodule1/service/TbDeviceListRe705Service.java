package com.example.druidmodule1.service;

import com.example.druidmodule1.entity.TbDeviceListRe705;
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
public interface TbDeviceListRe705Service extends IService<TbDeviceListRe705> {

    List<TbDeviceListRe705> findAllbyPage(Integer currentPage, Integer pagerow);

    int countAll();

    List<TbDeviceListRe705> searchAllbyPage(Integer pagerow,String deviceid, Integer currentPage, String orderId, String starttime, String endtime, String sn);

    List<TbDeviceListRe705> searchAllbyPagenum(String deviceid, Integer currentPage, String orderId, String starttime, String endtime,String sn);
}

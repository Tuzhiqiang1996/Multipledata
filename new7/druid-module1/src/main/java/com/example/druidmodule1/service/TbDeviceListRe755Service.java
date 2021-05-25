package com.example.druidmodule1.service;

import com.example.druidmodule1.entity.TbDeviceListRe755;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
public interface TbDeviceListRe755Service extends IService<TbDeviceListRe755> {

    List<TbDeviceListRe755> findAllbyPage(Integer currentPage, Integer pagerow);

    int countAll();

    List<TbDeviceListRe755> searchAllbyPage(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn);

    List<TbDeviceListRe755> searchAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime, String sn);
}

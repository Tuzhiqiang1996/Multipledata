package com.example.druidmodule1.service;

import com.example.druidmodule1.entity.TbDeviceLog;
import com.example.druidmodule1.entity.TbOrder;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
//import com.github.pagehelper.IPage;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
public interface TbOrderService extends IService<TbOrder> {

    List<TbOrder> findAllbyPage(Integer currentPage, Integer pagerow);

    List<TbOrder> findAllbyPagenum(String projectName, String orderNumber, Integer currentPage);
    int countAll();

}

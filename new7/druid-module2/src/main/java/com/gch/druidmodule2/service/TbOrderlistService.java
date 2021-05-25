package com.gch.druidmodule2.service;


import com.baomidou.mybatisplus.service.IService;
import com.gch.druidmodule2.entity.TbOrderlist;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-24
 */
public interface TbOrderlistService extends IService<TbOrderlist> {

    int countAll();
}

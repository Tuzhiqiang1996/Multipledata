package com.example.druidmodule1.service;

import com.example.druidmodule1.entity.MUser;
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
public interface MUserService extends IService<MUser> {

    List<MUser> findAllbyPage(int pagenum, int pagerow);

    int countAll();
}

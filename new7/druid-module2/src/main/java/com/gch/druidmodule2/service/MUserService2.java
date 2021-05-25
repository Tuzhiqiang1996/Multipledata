package com.gch.druidmodule2.service;

import com.baomidou.mybatisplus.service.IService;
import com.gch.druidmodule2.entity.MUser;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
public interface MUserService2 extends IService<MUser> {

    List<MUser> findAllbyPage(int pagenum, int pagerow);

    int countAll();
}

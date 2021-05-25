package com.gch.druidmodule2.service.impl;

import com.gch.druidmodule2.entity.TbOrderlist;
import com.gch.druidmodule2.mapper.TbOrderlistMapper;
import com.gch.druidmodule2.service.TbOrderlistService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tu
 * @since 2021-05-24
 */
@Service
public class TbOrderlistServiceImpl extends ServiceImpl<TbOrderlistMapper, TbOrderlist> implements TbOrderlistService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public int countAll() {
        String sql = "select * from tb_order ";
        List<TbOrderlist> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbOrderlist.class));
        return userList.size();
    }
}

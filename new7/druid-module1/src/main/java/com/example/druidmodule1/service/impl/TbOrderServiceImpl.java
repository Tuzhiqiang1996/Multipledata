package com.example.druidmodule1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.druidmodule1.entity.TbOrder;
import com.example.druidmodule1.mapper.TbOrderMapper;
import com.example.druidmodule1.service.TbOrderService;
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
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder> implements TbOrderService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TbOrder> findAllbyPage(Integer currentPage, Integer pagerow) {
        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_order  limit " + starter + " , " + pagerow;
        List<TbOrder> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbOrder.class));

        return list;
    }

    @Override
    public List<TbOrder> findAllbyPagenum(String projectName, String orderNumber, Integer currentPage) {
        String sql = "select * from tb_order WHERE ";
        String devid = " project_name  LIKE \"%" + projectName + "%\" ";
        String ordid = " order_number  LIKE \"%" + orderNumber + "%\" ";
        if (projectName != null && projectName.length() != 0) {
          String  sql1= devid;
            sql = "select * from tb_order WHERE "+sql1;
        }
        if (orderNumber != null && orderNumber.length() != 0) {

            String  sql1= ordid;
            sql = "select * from tb_order WHERE "+sql1;
        }
        if (orderNumber != null && orderNumber.length() != 0 && projectName != null && projectName.length() != 0) {
//            sql += ordid + " and " + devid;
            String  sql1= ordid + " and " + devid;
            sql = "select * from tb_order WHERE "+sql1;
        }
        List<TbOrder> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbOrder.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_order";
        List<TbOrder> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbOrder.class));
        return userList.size();
    }
}

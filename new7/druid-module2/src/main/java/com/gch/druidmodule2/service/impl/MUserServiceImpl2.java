package com.gch.druidmodule2.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gch.druidmodule2.entity.MUser;
import com.gch.druidmodule2.mapper.MUserMapper2;
import com.gch.druidmodule2.service.MUserService2;
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
public class MUserServiceImpl2 extends ServiceImpl<MUserMapper2, MUser> implements MUserService2 {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<MUser> findAllbyPage(int pagenum, int pagerow) {
        int starter = (pagenum-1)*pagerow;
        String sql = "select * from m_user order by id asc  limit " + starter +" , "+ pagerow;
        List<MUser> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MUser.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM m_user";
        List<MUser> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MUser.class));
//        List<MUser> userList = jdbcTemplate.query(sql, new RowMapper<MUser>() {
//            @Override
//            public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                return new MUser();
//            }
//        });
        return userList.size();
    }

}

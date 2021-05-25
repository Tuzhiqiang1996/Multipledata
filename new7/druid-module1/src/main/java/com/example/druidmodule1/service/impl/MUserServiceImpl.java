package com.example.druidmodule1.service.impl;

import com.example.druidmodule1.mapper.MUserMapper;
import com.example.druidmodule1.entity.MUser;
import com.example.druidmodule1.service.MUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<MUser > findAllbyPage(int pagenum, int pagerow) {
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

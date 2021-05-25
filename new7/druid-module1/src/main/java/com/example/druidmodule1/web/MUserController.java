package com.example.druidmodule1.web;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.druidmodule1.common.lang.Result;
import com.example.druidmodule1.entity.MUser;
import com.example.druidmodule1.service.MUserService;
import com.example.druidmodule1.util.JwtUtils;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Data
//@Controller
@RestController
@RequestMapping("/")
public class MUserController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    MUserService mUserService;
    @Autowired
    JwtUtils jwtUtils;

    DateTime dt = DateTime.now();

    /**
     * 查询单个基本数据类型(如String,Integer,Long等)和单个对象可以使用jdbcTemplate.queryForObject()方法，
     * 查询单个对象时 返回的数据结果空值的情况下 使用try/catch方法捕捉异常并进行处理
     * 查询基本数据类型列表可以使用jdbcTemplate.queryForList()方法，
     * 查询对象列表可以使用jdbcTemplate.query()或者jdbcTemplate.queryForList()
     * Objects.equals(stmd5, stmd52)
     * 对比两个字符串是否相同
     * 之前对比方式是错误的  之后改正
     * 登录
     */
    @PostMapping("login")
    public Result login(@RequestBody MUser mUser, HttpServletResponse response) {
        String username = mUser.getUsername();
        String password = mUser.getPassword();
//        QueryWrapper queryWrapper = new QueryWrapper<MUser>();
//        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//        QueryRunner qr = new QueryRunner();
        String sql = "SELECT * FROM m_user WHERE username=?";
        RowMapper<MUser> rowMapper = new BeanPropertyRowMapper<>(MUser.class);
        MUser user;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, username);

//            user = jdbcTemplate.queryForList(sql, rowMapper, username);
            String stmd5 = SecureUtil.md5(password);
            String stmd52 = user.getPassword();
            if (Objects.equals(stmd5, stmd52)) {
//                System.out.println(12);
                String jwt = jwtUtils.generateToken(user.getId());

                response.setHeader("Authorization", jwt);
                response.setHeader("Access-control-Expose-Headers", "Authorization");
            } else {
                return Result.fail("密码不正确");
            }

        } catch (EmptyResultDataAccessException e) {
//            return Result.fail(e.getLocalizedMessage());
            return Result.fail("用户不存在", "405");
        }
        System.out.println("登录成功！");
        return Result.succ("登录成功！", MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .put("status", user.getStatus())
                .map());

    }

    /**
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/8 9:48
     * @message 登出 @RequiresAuthentication 请求头加token
     */
//    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("登出成功！");
    }

    /**
     * [com.example.entity.MUser]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/8 10:44
     * @message 注册
     */
    @PostMapping("/regina")
    public Result regina(@RequestBody MUser user) {
        String username = user.getUsername();
        String sql = "SELECT username FROM m_user WHERE username=?";
        RowMapper<MUser> rowMapper = new BeanPropertyRowMapper<>(MUser.class);
        MUser user1 = null;
        System.out.println();
        try {
            user1 = jdbcTemplate.queryForObject(sql, rowMapper, username);
            return Result.fail("用户名存在", "405");
        } catch (EmptyResultDataAccessException e) {

        }
        if (user.getPassword() == null || user.getAvatar() == null || user.getUsername() == null || user.getStatus() == null || user.getEmail() == null) {
            return Result.fail("填写信息不全！");
        }
        MUser userRegina = new MUser();
        //加密
        String pass = user.getPassword();
        userRegina.setUsername(user.getUsername());
        userRegina.setPassword(SecureUtil.md5(pass));
        userRegina.setAvatar(user.getAvatar());
        userRegina.setEmail(user.getEmail());
        userRegina.setCreated(dt);
        userRegina.setStatus(user.getStatus());
        mUserService.insertOrUpdate(userRegina);
        return Result.succ("注册成功！");
    }

    /**
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/7 17:58
     * @message 获取用户列表 全部  未分页
     */
    @GetMapping("/userlist")
    public Result userlist() {
        String sql = "SELECT * FROM m_user";
        List<MUser> userList = jdbcTemplate.query(sql, new RowMapper<MUser>() {
            MUser user = null;

            @Override
            public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new MUser();
                user.setId(rs.getLong("Id"));
                user.setUsername(rs.getString("username"));
                user.setAvatar(rs.getString("avatar"));
                user.setCreated(rs.getDate("created"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getInt("status"));
                user.setLastLogin(rs.getDate("last_login"));
                return user;
            }
        });

        return Result.succ("操作成功！", userList);
    }


    /**
     * [java.lang.Integer, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/8 11:55
     * @message pageNum 第几页 从0开始 pageSize一页的数量 created asc降序
     * 问题 看不到总的数量 总的列数 只能获取区间数据 不符合
     */
    @GetMapping("getPage")
    public Result roleAllListPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = 5;
        }
        String sql = "SELECT * FROM m_user ORDER BY created asc LIMIT ?,?";
        int startIndex = pageNum * pageSize;//提取分页开始索引
        List<MUser> list = jdbcTemplate.query(sql, new Object[]{startIndex, pageSize}, new BeanPropertyRowMapper<>(MUser.class));
        return Result.succ("", list);
    }

    /**
     * [java.lang.Integer, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/8 13:40
     * @message 完成ok
     * pagenum  第几页
     * pagerow  每页的数量
     */
    @GetMapping("userList")
    public Result getuserlist(Integer currentPage) {
        if (currentPage == null || currentPage < 0) {
            currentPage = 1;
        }


        Page iPage = new Page<>(currentPage, 8);
        EntityWrapper<MUser> entityWrapper = new EntityWrapper<>();
        iPage = mUserService.selectPage(iPage, entityWrapper);

        if (iPage == null ) {
            return Result.fail("没有数据！");
        }

        JSONObject jsonObject = new JSONObject(iPage);//可以将json格式的字符串变成json对象
        JSONArray jsonArray = (JSONArray) jsonObject.get("records");
//        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonData = (JSONObject) jsonArray.get(i);//得到对象中的第i条记录
            jsonData.remove("password");
//            System.out.println("data[" + i + "]:" + jsonData.remove("password"));
        }
        return Result.succ("获取成功！", jsonObject);
    }

    /**
     * [java.lang.Long, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/8 16:21
     * @message 删除用户
     */
    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestParam Long id, @RequestParam Integer status) {
//前端status 当前操作者 只有超级管理员才有权限
        if (status != 0) {
            return Result.fail("没有权限");
        }
//        boolean userid = mUserService.deleteById(id);
        mUserService.deleteById(id);
//        System.out.println(userid);
//        System.out.println(userid);
//        mUserService.removeById(userid);
        return Result.succ("删除成功", null);
    }

    @PostMapping("/user/edit")
    public Result useredit(@Validated @RequestBody MUser user) {
        EntityWrapper<MUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", user.getId());
        //根据当前id获取用户信息
        MUser dd = mUserService.selectById(user.getId());

        //设置更改时间
        dd.setLastLogin(dt);
        if (user.getUsername() != null && user.getUsername().length() != 0) {
            dd.setUsername(user.getUsername());
            System.out.println("name修改");
        }
        if (user.getEmail() != null && user.getEmail().length() != 0) {
            dd.setEmail(user.getEmail());
            System.out.println("邮箱修改");
        }
        if (user.getStatus() != null) {
            dd.setStatus(user.getStatus());
            System.out.println("权限修改");
        }
        mUserService.insertOrUpdate(dd);
//        mUserService.update(dd, entityWrapper);
//        System.out.println(jsonObject);
        JSONObject jsonObject = new JSONObject(dd);//可以将json格式的字符串变成json对象
        jsonObject.remove("password");//过滤的值
        return Result.succ("修改成功！", jsonObject);
    }

    @PostMapping("/user/pass")
    public Result userpass(@RequestParam Integer id, @RequestParam String password, @RequestParam String newpassword) {
//        System.out.println(id + "" + password + "" + newpassword);

        MUser dd = mUserService.selectById(id);
        if (password != null && password.length() != 0) {

            if (SecureUtil.md5(password).equals(dd.getPassword())) {
                dd.setPassword(SecureUtil.md5(newpassword));
                dd.setLastLogin(dt);
                System.out.println("密码修改");
                mUserService.insertOrUpdate(dd);

            } else {
                System.out.println("密码效验错误");
                return Result.fail("密码效验错误！");
            }

        }
//        System.out.println(dd);
        JSONObject jsonObject = new JSONObject(dd);//可以将json格式的字符串变成json对象
        jsonObject.remove("password");//过滤的值
        return Result.succ("修改成功!", jsonObject);
    }

    @GetMapping("/getlisttu6")
    public Result getlist66(Integer currentPage) {
        if (currentPage == null || currentPage < 0) {
            currentPage = 1;
        }
        Page iPage = new Page<>(currentPage, 8);
        System.out.println(iPage);
//        查询不出结果 0  第一种  可以查询 依赖要导入到本模块中
        EntityWrapper<MUser> entityWrapper = new EntityWrapper<>();
        iPage = mUserService.selectPage(iPage, entityWrapper);
        System.out.println(iPage);
        return Result.succ(iPage);
    }
}


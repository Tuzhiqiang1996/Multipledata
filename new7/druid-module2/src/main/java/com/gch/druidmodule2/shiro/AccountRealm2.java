package com.gch.druidmodule2.shiro;

/**
 * @author Tu
 * @Package com.example.shiro
 * @date 2021/2/1-10:05
 */

import cn.hutool.core.bean.BeanUtil;
import com.gch.druidmodule2.entity.MUser;
import com.gch.druidmodule2.service.MUserService2;
import com.gch.druidmodule2.util.JwtUtils2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AccountRealm是shiro进行登录或者权限校验的逻辑所在，
 * 算是核心了，我们需要重写3个方法，分别是
 * <p>
 * supports：为了让realm支持jwt的凭证校验
 * doGetAuthorizationInfo：权限校验
 * doGetAuthenticationInfo：登录认证校验
 * @author Administrator
 */
@Component
public class AccountRealm2 extends AuthorizingRealm {
    @Autowired
    JwtUtils2 jwtUtils;
    @Autowired
    MUserService2 mUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken2;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken2 jwtToken = (JwtToken2) token;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        MUser user = mUserService.selectById(Long.valueOf(userId));
//        MUser user = null;
        if (user == null) {
            throw new UnknownAccountException("账户不存在");

        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");

        }
        AccountProfile2 profile = new AccountProfile2();
        BeanUtil.copyProperties(user,profile);
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}

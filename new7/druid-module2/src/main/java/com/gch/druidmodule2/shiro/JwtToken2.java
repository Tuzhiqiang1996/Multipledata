package com.gch.druidmodule2.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Tu
 * @Package com.example.shiro
 * @date 2021/2/1-10:52
 */
public class JwtToken2 implements AuthenticationToken {
    private String token;
    public JwtToken2(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}


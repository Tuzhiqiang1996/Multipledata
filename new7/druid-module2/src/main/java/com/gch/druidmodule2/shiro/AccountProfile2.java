package com.gch.druidmodule2.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tu
 * @Package com.example.shiro
 * @date 2021/2/1-10:49
 */
@Data
public class AccountProfile2 implements Serializable {
    private Long id;
    private String userName;
    private String avatar;
    private String email;

}

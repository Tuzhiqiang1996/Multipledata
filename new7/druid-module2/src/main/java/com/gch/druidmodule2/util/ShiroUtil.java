package com.gch.druidmodule2.util;

import com.gch.druidmodule2.shiro.AccountProfile2;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile2 getProfile() {
        return (AccountProfile2) SecurityUtils.getSubject().getPrincipal();
    }

}

package com.company;

import com.company.MyEnum.RoleEnum;
import com.company.factory.RoleFactory;

/**
 * @author:
 * @date: created in 9:12 2020/8/11
 * @version:
 */
public class ifelse {
    public String jude(String roleName){
        String result="";
        if ("ROLE_ROOT_ADMIN".equals(roleName)){
            result="ROLE_ROOT_ADMIN"+"has AAAAA";
        }else if ("ROLE_ORDER_ADMIN".equals(roleName)){
            result = "ROLE_ORDER_ADMIN" + "has BBBBB";
        }else if ("ROLE_NORMAL".equals(roleName)) {
            result = "ROLE_NORMAL" + "has CCCCCC";
        }else {
            result ="XXXXX";
        }
        return result;
    }
    public String jude1(String roleName){
        return RoleEnum.valueOf(roleName).op();
    }

    public String jude2(String roleName){
        return RoleFactory.getOp(roleName).op();
    }
}
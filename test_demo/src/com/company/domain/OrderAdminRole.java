package com.company.domain;

import com.dao.RoleOperation;

/**
 * @author:
 * @date: created in 9:32 2020/8/11
 * @version:
 */
public class OrderAdminRole implements RoleOperation {
    private String roleName;

    public OrderAdminRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName+"has BBBBB";
    }
}
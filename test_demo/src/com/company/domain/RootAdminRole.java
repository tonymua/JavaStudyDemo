package com.company.domain;

import com.dao.RoleOperation;

/**
 * @author:
 * @date: created in 9:30 2020/8/11
 * @version:
 */
public class RootAdminRole implements RoleOperation {
    private String roleName;

    public RootAdminRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName+"has AAAAA";
    }
}
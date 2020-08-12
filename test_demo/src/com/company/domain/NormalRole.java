package com.company.domain;

import com.dao.RoleOperation;

/**
 * @author:
 * @date: created in 9:33 2020/8/11
 * @version:
 */
public class NormalRole implements RoleOperation {
    private String roleName;

    public NormalRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName+"has CCCCC";
    }
}
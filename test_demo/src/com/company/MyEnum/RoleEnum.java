package com.company.MyEnum;

import com.dao.RoleOperation;

public enum RoleEnum implements RoleOperation {
    ROLE_ROOT_ADMIN{
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN"+"has AAAAA";
        }
    },
    ROLE_ORDER_ADMIN{
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN" + "has BBBBB";
        }
    },
    ROLE_NORMAL{
        @Override
        public String op() {
            return"ROLE_NORMAL" + "has CCCCCC";
        }
    }
}

package com.example.serviceacl.utils;

import com.example.commonutils.entity.AclPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * create by Freedom on 2020/11/26
 */
public class RecursionMenu {

    public List<AclPermission> build(List<AclPermission> permissionList, String id){

        List<AclPermission> result = new ArrayList<>();

        for (AclPermission permission : permissionList) {
            if (permission.getPid().equals(id)) {
                result.add(findChildren(permission,permissionList));
            }
        }

        return result;
    }

    private AclPermission findChildren(AclPermission permission, List<AclPermission> permissionList) {

        if (permission.getChildren() ==null){
            permission.setChildren(new ArrayList<>());
        }

        for (AclPermission temp : permissionList) {
            if (permission.getId().equals(temp.getPid())){
                permission.getChildren().add(findChildren(temp, permissionList));
            }
        }
        return permission;
    }

}

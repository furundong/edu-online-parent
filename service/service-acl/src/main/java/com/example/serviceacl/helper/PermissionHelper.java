package com.example.serviceacl.helper;


import com.example.commonutils.entity.AclPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *
 * @author qy
 * @since 2019-11-11
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<AclPermission> bulid(List<AclPermission> treeNodes) {
        List<AclPermission> trees = new ArrayList<>();
        for (AclPermission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                AclPermission children = findChildren(treeNode, treeNodes);
                trees.add(children);
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static AclPermission findChildren(AclPermission treeNode,List<AclPermission> treeNodes) {
        treeNode.setChildren(new ArrayList<>());
        for (AclPermission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}

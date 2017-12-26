package com.xingrongjinfu.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.xingrongjinfu.system.permission.model.Permission;

/**
 * 权限数据处理
 * 
 * @author y
 */
public class   TreeUtil
{

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */
    public static List<Permission> getChildPerms(List<Permission> list, int praentId)
    {
        List<Permission> returnList = new ArrayList<Permission>();
        for (Iterator<Permission> iterator = list.iterator(); iterator.hasNext();)
        {
            Permission t = (Permission) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == praentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param Permission
     */
    private static void recursionFn(List<Permission> list, Permission t)
    {
        List<Permission> childList = getChildList(list, t);// 得到子节点列表
        t.setChildren(childList);
        for (Permission tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<Permission> it = childList.iterator();
                while (it.hasNext())
                {
                    Permission n = (Permission) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    // 得到子节点列表
    private static List<Permission> getChildList(List<Permission> list, Permission t)
    {

        List<Permission> tlist = new ArrayList<Permission>();
        Iterator<Permission> it = list.iterator();
        while (it.hasNext())
        {
            Permission n = (Permission) it.next();
            if (n.getParentId().intValue() == t.getPermsId().intValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<Permission> returnList = new ArrayList<Permission>();

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<Permission> getChildPerms(List<Permission> list, int typeId, String prefix)
    {
        if (list == null)
            return null;
        for (Iterator<Permission> iterator = list.iterator(); iterator.hasNext();)
        {
            Permission node = (Permission) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId)
            {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<Permission> list, Permission node, String p)
    {
        List<Permission> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node))
        {// 判断是否有子节点
            returnList.add(node);
            Iterator<Permission> it = childList.iterator();
            while (it.hasNext())
            {
                Permission n = (Permission) it.next();
                n.setPermsName(p + n.getPermsName());
                recursionFn(list, n, p + p);
            }
        }
        else
        {
            returnList.add(node);
        }
    }

    // 判断是否有子节点
    private static boolean hasChild(List<Permission> list, Permission t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    // 本地模拟数据测试
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        List<Permission> permList = new ArrayList<Permission>();

        Permission perm1 = new Permission();
        perm1.setPermsId(100);
        perm1.setPermsName("系统管理");
        perm1.setParentId(0);

        Permission perm2 = new Permission();
        perm2.setPermsId(101);
        perm2.setPermsName("用户管理");
        perm2.setParentId(100);

        Permission perm3 = new Permission();
        perm3.setPermsId(102);
        perm3.setPermsName("角色管理");
        perm3.setParentId(100);

        Permission perm4 = new Permission();
        perm4.setPermsId(103);
        perm4.setPermsName("菜单管理");
        perm4.setParentId(100);

        Permission perm5 = new Permission();
        perm5.setPermsId(103);
        perm5.setPermsName("日志管理");
        perm5.setParentId(100);

        permList.add(perm1);
        permList.add(perm2);
        permList.add(perm3);
        permList.add(perm4);
        permList.add(perm5);

        List<Permission> ns = TreeUtil.getChildPerms(permList, 0);
        for (Permission m : ns)
        {
            System.out.println(m.getPermsName());
            System.out.println(m.getChildren());
        }
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start) + "ms");

    }

}

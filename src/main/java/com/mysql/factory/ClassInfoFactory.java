package com.mysql.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.mysql.bean.ClassInfo;
import com.mysql.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ******************************
 * author：      柯贤铭
 * createTime:   2020/1/9 18:24
 * description:  ClassInfoFactory
 * version:      V1.0
 * ******************************
 */
public class ClassInfoFactory {

    private static final List<ClassInfo> CLASS_INFO_LIST = new ArrayList<>();

    public static List<ClassInfo> getClassInfoList() {
        if (CollectionUtil.isEmpty(CLASS_INFO_LIST)) {
            synchronized (ClassInfoFactory.class) {
                if (CollectionUtil.isEmpty(CLASS_INFO_LIST)) {
                    try {
                        List<String> tableNames = DataBaseUtil.getAllTableNames();
                        for (String tableName : tableNames) {
                            ClassInfo classInfo = DataBaseUtil.parseClassInfo(tableName);
                            CLASS_INFO_LIST.add(classInfo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return CLASS_INFO_LIST;
    }
}

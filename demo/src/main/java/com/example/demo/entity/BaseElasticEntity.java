package com.example.demo.entity;

/**
 * Elastic基类
 * @author YanCh
 * Create by 2020-03-05 10:13
 **/
public class BaseElasticEntity {
    /**
     * id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * es索引名称（项目启动时初始化创建索引）
     */
    public final static String[] INDEX_NAMES = {
            "item"
    };

    /**
     * es索引设置相对路径
     */
    public final static String[] INDEX_SETTING_PATHS = {
            "/json/base-setting.json"
    };

    /**
     *
     */
    public final static String[] INDEX_MAPPING_PATHS = {
            "/json/item-mapping.json"
    };
}

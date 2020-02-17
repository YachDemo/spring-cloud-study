package com.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * 
 * @author YanCh
 * Create by 2020-02-15 10:23
 **/
@Data
@Document(indexName = "item",type = "docs",shards = 1,replicas = 0)
@Mapping(mappingPath = "/json/item-mapping.json")
@Setting(settingPath = "/json/item-setting.json")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
//    @Id
    private Long id;

    /**
     * 标题
     */
//    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 分类
     */
//    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 品牌
     */
//    @Field(type = FieldType.Keyword)
    private String brand;

    /**
     * 价格
     */
//    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 图片地址
     */
//    @Field(index = false, type = FieldType.Keyword)
    private String images;
}

package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author YanCh
 * Create by 2020-02-15 10:23
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Item extends BaseElasticEntity {
    /**
     * 标题
     */

    private String title;

    /**
     * 分类
     */

    private String category;

    /**
     * 品牌
     */

    private String brand;

    /**
     * 价格
     */
    private Double price;

    /**
     * 图片地址
     */
    private String images;
}

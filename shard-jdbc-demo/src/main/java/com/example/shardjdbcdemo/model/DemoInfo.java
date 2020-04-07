package com.example.shardjdbcdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2020-03-31 15:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_demo_info")
public class DemoInfo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "is_bind")
    private Boolean isBind;

    @TableField(value = "create_time")
    private Integer createTime;

    @TableField(value = "update_time")
    private Long updateTime;
}
package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @author YanCh
 * Create by 2020-02-26 16:22
 **/
@Data
@TableName("t_demo_info")
public class Demo {
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer isBind;
    private Long createTime;
    private Long updateTime;
}

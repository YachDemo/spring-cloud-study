package com.mssql.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-23 13:13
 **/
@Data
public class KqInfo implements Serializable {
    private String kqId;

    private String kqMoney;

    private String kqPsw;

    private Date kqYxq;

    private String state;

    private String fqMallName;

    private String fqCkName;

    private String hxMallName;

    private String hxCkName;

    private BigDecimal hxMoney;

    private String hxPerson;

    private Date hxTime;

    private Date creatTime;

    private String creatPerson;

    private Date chgTime;

    private String chgPerson;

    private String bzMemo;

    private String hxMemo;

    private String kqName;

    private String hxDhId;

    private String useTj;

    private String xfMe;

    private static final long serialVersionUID = 1L;
}
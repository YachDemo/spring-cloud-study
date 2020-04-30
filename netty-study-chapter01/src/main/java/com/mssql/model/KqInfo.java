package com.mssql.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-23 13:11
 **/
public class KqInfo implements Serializable {
    private String kqId;

    private Object kqMoney;

    private String kqPsw;

    private Date kqYxq;

    private String state;

    private String fqMallName;

    private String fqCkName;

    private String hxMallName;

    private String hxCkName;

    private Object hxMoney;

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

    private Object xfMe;

    private static final long serialVersionUID = 1L;

    public String getKqId() {
        return kqId;
    }

    public void setKqId(String kqId) {
        this.kqId = kqId;
    }

    public Object getKqMoney() {
        return kqMoney;
    }

    public void setKqMoney(Object kqMoney) {
        this.kqMoney = kqMoney;
    }

    public String getKqPsw() {
        return kqPsw;
    }

    public void setKqPsw(String kqPsw) {
        this.kqPsw = kqPsw;
    }

    public Date getKqYxq() {
        return kqYxq;
    }

    public void setKqYxq(Date kqYxq) {
        this.kqYxq = kqYxq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFqMallName() {
        return fqMallName;
    }

    public void setFqMallName(String fqMallName) {
        this.fqMallName = fqMallName;
    }

    public String getFqCkName() {
        return fqCkName;
    }

    public void setFqCkName(String fqCkName) {
        this.fqCkName = fqCkName;
    }

    public String getHxMallName() {
        return hxMallName;
    }

    public void setHxMallName(String hxMallName) {
        this.hxMallName = hxMallName;
    }

    public String getHxCkName() {
        return hxCkName;
    }

    public void setHxCkName(String hxCkName) {
        this.hxCkName = hxCkName;
    }

    public Object getHxMoney() {
        return hxMoney;
    }

    public void setHxMoney(Object hxMoney) {
        this.hxMoney = hxMoney;
    }

    public String getHxPerson() {
        return hxPerson;
    }

    public void setHxPerson(String hxPerson) {
        this.hxPerson = hxPerson;
    }

    public Date getHxTime() {
        return hxTime;
    }

    public void setHxTime(Date hxTime) {
        this.hxTime = hxTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatPerson() {
        return creatPerson;
    }

    public void setCreatPerson(String creatPerson) {
        this.creatPerson = creatPerson;
    }

    public Date getChgTime() {
        return chgTime;
    }

    public void setChgTime(Date chgTime) {
        this.chgTime = chgTime;
    }

    public String getChgPerson() {
        return chgPerson;
    }

    public void setChgPerson(String chgPerson) {
        this.chgPerson = chgPerson;
    }

    public String getBzMemo() {
        return bzMemo;
    }

    public void setBzMemo(String bzMemo) {
        this.bzMemo = bzMemo;
    }

    public String getHxMemo() {
        return hxMemo;
    }

    public void setHxMemo(String hxMemo) {
        this.hxMemo = hxMemo;
    }

    public String getKqName() {
        return kqName;
    }

    public void setKqName(String kqName) {
        this.kqName = kqName;
    }

    public String getHxDhId() {
        return hxDhId;
    }

    public void setHxDhId(String hxDhId) {
        this.hxDhId = hxDhId;
    }

    public String getUseTj() {
        return useTj;
    }

    public void setUseTj(String useTj) {
        this.useTj = useTj;
    }

    public Object getXfMe() {
        return xfMe;
    }

    public void setXfMe(Object xfMe) {
        this.xfMe = xfMe;
    }
}
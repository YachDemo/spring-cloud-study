package com.study.enums;

/**
 * 返回值枚舉類
 * @author YanCh
 * Create by 2019-12-30 15:34
 **/
public enum ResultEnum {
    SUCCESS("0000", "操作成功！"),
    ERROR("9999", "操作失敗！");

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

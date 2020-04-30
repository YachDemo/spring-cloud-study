package com.study.service;

/**
 * 消息推送接口
 *
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-30 10:25
 **/
public interface PushService {

    /**
     * 推送给用户
     *
     * @param userId userId
     * @param msg    msg
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送所有用户
     */
    void pushMsgToAll(String msg);

}

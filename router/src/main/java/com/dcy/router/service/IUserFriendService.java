package com.dcy.router.service;

import com.dcy.router.model.UserFriend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户好友表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
public interface IUserFriendService extends IService<UserFriend> {


    /**
     * 添加好友
     *
     * @param fromUserId 谁添加
     * @param toUserId   好友id
     * @return
     */
    Boolean addFriend(String fromUserId, String toUserId);

    /**
     * 删除好友
     *
     * @param fromUserId
     * @param toUserId
     * @return
     */
    Boolean deleteFriend(String fromUserId, String toUserId);

    /**
     * 查询好友列表
     *
     * @param userId
     * @return
     */
    List<UserFriend> getFriendList(String userId);

}

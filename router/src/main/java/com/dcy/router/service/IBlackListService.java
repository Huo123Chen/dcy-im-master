package com.dcy.router.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dcy.router.model.BlackList;
import com.dcy.router.model.User;

import java.util.List;

/**
 * <p>
 * 黑名单表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-08-03
 */
public interface IBlackListService extends IService<BlackList> {


    /**
     * 添加黑名单
     *
     * @param fromUserId 谁添加
     * @param toUserId   好友id
     * @return
     */
    Boolean addBlackList(String fromUserId, String toUserId);

    /**
     * 删除黑名单
     * @param fromUserId 谁添加
     * @param toUserId   好友id
     * @return
     */
    Boolean deleteBlackList(String fromUserId, String toUserId);

}

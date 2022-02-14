package com.dcy.router.service;

import com.dcy.router.model.GroupUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 组用户表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
public interface IGroupUserService extends IService<GroupUser> {
    /**
     * 删除群组所有成员
     * @param groupId
     * @return
     */
    Boolean deleteAllGroupUser(String groupId);

    /**
     * 删除群组成员
     * @param groupId
     * @return
     */
    Boolean deleteGroupUser(String groupId,String userId);
}

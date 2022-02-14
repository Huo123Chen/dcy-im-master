package com.dcy.router.mapper;

import com.dcy.router.model.UserFriend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户好友表 Mapper 接口
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
public interface UserFriendMapper extends BaseMapper<UserFriend> {

    List<UserFriend> getFriendList(String userId);

}

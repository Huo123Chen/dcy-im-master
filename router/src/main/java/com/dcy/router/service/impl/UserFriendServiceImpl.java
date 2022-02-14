package com.dcy.router.service.impl;

import com.dcy.router.mapper.UserFriendMapper;
import com.dcy.router.model.UserFriend;
import com.dcy.router.service.IUserFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户好友表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@Service
@Transactional
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements IUserFriendService {

    @Autowired
    private UserFriendMapper userFriendMapper;

    @Override
    public Boolean addFriend(String fromUserId, String toUserId) {
        return baseMapper.insert(new UserFriend().setUserId(fromUserId).setFriendId(toUserId)) > 0
                && baseMapper.insert(new UserFriend().setUserId(toUserId).setFriendId(fromUserId)) > 0;
    }

    @Override
    public Boolean deleteFriend(String fromUserId, String toUserId) {
        return baseMapper.deleteById(fromUserId) > 0
                && baseMapper.deleteById(toUserId) > 0;
    }

    @Override
    public List<UserFriend> getFriendList(String userId){
        return userFriendMapper.getFriendList(userId);
    }

}

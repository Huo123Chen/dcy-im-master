package com.dcy.router.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcy.common.utils.UuidUtil;
import com.dcy.router.mapper.BlackListMapper;
import com.dcy.router.model.BlackList;
import com.dcy.router.model.UserFriend;
import com.dcy.router.service.IBlackListService;
import com.dcy.router.service.IUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 黑名单表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-08-03
 */
@Service
@Transactional
public class BlackListServiceImpl extends ServiceImpl<BlackListMapper, BlackList> implements IBlackListService {

    @Autowired
    private IUserFriendService iUserFriendService;

    @Override
    public Boolean addBlackList(String fromUserId, String toUserId) {
        return baseMapper.insert(new BlackList().setId(UuidUtil.generateUuid())
                .setUserId(fromUserId).setBlackUserId(toUserId)) > 0;
    }

    @Override
    public Boolean deleteBlackList(String fromUserId, String toUserId) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("user_id", fromUserId);
        columnMap.put("black_user_id", toUserId);
        int result = baseMapper.deleteByMap(columnMap);
        return result > 0;
    }
}

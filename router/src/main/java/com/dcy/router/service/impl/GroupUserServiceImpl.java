package com.dcy.router.service.impl;

import com.dcy.router.mapper.GroupUserMapper;
import com.dcy.router.model.GroupUser;
import com.dcy.router.service.IGroupUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 组用户表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@Service
@Transactional
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {

    @Override
    public Boolean deleteAllGroupUser(String groupId) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("group_id", groupId);
        int result = baseMapper.deleteByMap(columnMap);
        return result > 0;
    }

    @Override
    public Boolean deleteGroupUser(String groupId,String userId) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("group_id", groupId);
        columnMap.put("user_id", userId);
        int result = baseMapper.deleteByMap(columnMap);
        return result > 0;
    }
}

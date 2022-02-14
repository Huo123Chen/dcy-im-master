package com.dcy.router.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.router.mapper.GroupMapper;
import com.dcy.router.mapper.GroupUserMapper;
import com.dcy.router.model.Group;
import com.dcy.router.model.GroupUser;
import com.dcy.router.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 群组表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@Service
@Transactional
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Override
    public Boolean createGroup(Group group) {
        if (baseMapper.insert(group) > 0) {
            if (CollUtil.isNotEmpty(group.getUserIds())) {
                for (String userId : group.getUserIds()) {
                    GroupUser groupUser = new GroupUser();
                    groupUser.setUserId(userId).setGroupId(group.getId());
                    groupUserMapper.insert(groupUser);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteGroup(String groupId) {
        List<String> userIds = groupUserMapper.selectList(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getGroupId, groupId))
                .stream().map(GroupUser::getUserId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(userIds)) {
            for (String userId : userIds) {
                groupUserMapper.deleteById(userId);
            }
        }
        if (baseMapper.deleteById(groupId) > 0) {
            return true;
        }
        return false;
    }
}

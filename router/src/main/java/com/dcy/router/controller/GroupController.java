package com.dcy.router.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import com.dcy.router.model.GroupUser;
import com.dcy.router.model.User;
import com.dcy.router.model.UserFriend;
import com.dcy.router.service.IGroupUserService;
import com.dcy.router.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dcy.router.model.Group;
import com.dcy.router.service.IGroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 群组表 前端控制器
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService iGroupService;
    @Autowired
    private IGroupUserService iGroupUserService;
    @Autowired
    private IUserService iUserService;


    /**
     * 群组列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/getGroupByUserId")
    public ResponseData<List<Group>> getFriendsByUserId(String userId) {
        List<GroupUser> groupUsers = iGroupUserService.list(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserId, userId));
        if (CollUtil.isNotEmpty(groupUsers)) {
            List<String> groupIdList = groupUsers.stream().map(GroupUser::getGroupId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(groupIdList)) {
                return ResponseData.success(iGroupService.list(Wrappers.<Group>lambdaQuery().in(Group::getId, groupIdList)));
            }
        }
        return ResponseData.success(new ArrayList<>());
    }


    /**
     * 获取群组的用户
     *
     * @param groupId
     * @return
     */
    @GetMapping("/getUserByGroupId")
    public ResponseData<List<User>> getUserByGroupId(String groupId) {
        List<GroupUser> groupUsers = iGroupUserService.list(Wrappers.<GroupUser>lambdaQuery().in(GroupUser::getGroupId, groupId));
        if (CollUtil.isNotEmpty(groupUsers)) {
            List<String> userIdList = groupUsers.stream().map(GroupUser::getUserId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(userIdList)) {
                return ResponseData.success(iUserService.list(Wrappers.<User>lambdaQuery().in(User::getId, userIdList)));
            }
        }
        return ResponseData.success(new ArrayList<>());
    }

    // 创建群组
    @PostMapping("/createGroup")
    public ResponseData<Boolean> createGroup(@RequestBody Group group) {

        return ResponseData.success(iGroupService.createGroup(group));
    }


    // 删除群组
    @PostMapping("/deleteGroup")
    public ResponseData<Boolean> deleteGroup(String groupId) {
        return ResponseData.success(iGroupService.deleteGroup(groupId));
    }


}

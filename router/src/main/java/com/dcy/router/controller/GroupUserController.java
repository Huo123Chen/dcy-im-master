package com.dcy.router.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import com.dcy.router.model.GroupUser;
import com.dcy.router.model.User;
import com.dcy.router.service.IGroupUserService;
import com.dcy.router.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：ly
 * @Description:群组人员关系表 前端控制器
 * @Date: 2020/8/7 0007 下午 1:49
 */
@RestController
@RequestMapping("/group-user")
public class GroupUserController {

    @Autowired
    private IGroupUserService iGroupUserService;

    @Autowired
    private IUserService iUserService;

    /**
     * 获取群组的用户
     *
     * @param groupId
     * @return
     */
    @GetMapping("/getUserListByGroupId")
    public ResponseData<List<GroupUser>> getUserListByGroupId(String groupId) {
        List<GroupUser> groupUsers = iGroupUserService.list(Wrappers.<GroupUser>lambdaQuery().in(GroupUser::getGroupId, groupId));
        if (CollUtil.isNotEmpty(groupUsers)) {
            return ResponseData.success(groupUsers);
        }
        return ResponseData.success(new ArrayList<>());
    }

    /**
     * 检查是否为群组成员
     *
     * @param groupId userId
     * @return
     */
    @GetMapping("/isOrNotGroupUser")
    public ResponseData<Boolean> isOrNotGroupUser(String groupId,String userId){
        Boolean ret = false;
        GroupUser groupuser = iGroupUserService.getOne(Wrappers.<GroupUser>lambdaQuery()
                .eq(GroupUser::getGroupId, groupId).eq(GroupUser::getUserId,userId));
        if(ObjectUtil.isNotNull(groupuser)){
            ret = true;
        }
        return ResponseData.success(ret);
    }

    /**
     * 在群组中禁言某个用户、解除禁言
     * 禁言 isTalk传0  解禁传1
     * @param groupUser
     * @return
     */
    @PostMapping("/updateTalkStatus")
    public ResponseData<Boolean> updateTalkStatus(@RequestBody GroupUser groupUser){
        Boolean ret = iGroupUserService.update(groupUser,Wrappers.<GroupUser>lambdaQuery()
                .eq(GroupUser::getGroupId,groupUser.getGroupId()).eq(GroupUser::getUserId,groupUser.getUserId()));
        return ResponseData.success(ret);
    }

    /**
     * 检查是否被禁言
     * @param groupId  userId
     * @return true 禁言    false 正常
     */
    @GetMapping("/isOrNotTalk")
    public ResponseData<Boolean> isOrNotTalk(String groupId,String userId){
        Boolean ret = false;
        GroupUser groupuser = iGroupUserService.getOne(Wrappers.<GroupUser>lambdaQuery()
                .eq(GroupUser::getGroupId, groupId).eq(GroupUser::getUserId,userId));
        if(ObjectUtil.isNotNull(groupuser) && 0 == groupuser.getIsTalk()){
            ret = true;
        }else if(ObjectUtil.isNull(groupuser)){
            return ResponseData.success("群组中不存在此用户",ret);
        }
        return ResponseData.success(ret);
    }

    /**
     * 删除群组所有成员
     * @param groupId
     * @return
     */
    @PostMapping("/deleteAllGroupUser")
    public ResponseData<Boolean> deleteAllGroupUser(String groupId){
        return ResponseData.success(iGroupUserService.deleteAllGroupUser(groupId));
    }

    /**
     * 检查是否为群主
     *
     * @param groupId userId
     * @return
     */
    @GetMapping("/isOrNotGroupManager")
    public ResponseData<Boolean> isOrNotGroupManager(String groupId,String userId){
        Boolean ret = false;
        GroupUser groupuser = iGroupUserService.getOne(Wrappers.<GroupUser>lambdaQuery()
                .eq(GroupUser::getGroupId, groupId).eq(GroupUser::getUserId,userId)
                .eq(GroupUser::getUserRole,0));
        if(ObjectUtil.isNotNull(groupuser)){
            ret = true;
        }
        return ResponseData.success(ret);
    }

    /**
     * 删除群组某个成员
     * @param groupId
     * @return
     */
    @PostMapping("/deleteGroupUser")
    public ResponseData<Boolean> deleteGroupUser(String groupId,String userId){
        return ResponseData.success(iGroupUserService.deleteGroupUser(groupId,userId));
    }

}

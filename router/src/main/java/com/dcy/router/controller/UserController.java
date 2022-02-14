package com.dcy.router.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import com.dcy.router.model.UserFriend;
import com.dcy.router.service.IUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dcy.router.model.User;
import com.dcy.router.service.IUserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserFriendService iUserFriendService;


    /**
     * 好友列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/getFriendsByUserId")
    public ResponseData<List<User>> getFriendsByUserId(String userId) {
        //List<UserFriend> userFriends = iUserFriendService.list(Wrappers.<UserFriend>lambdaQuery().eq(UserFriend::getUserId, userId));
        List<UserFriend> userFriends = iUserFriendService.getFriendList(userId);
        if (CollUtil.isNotEmpty(userFriends)) {
            List<String> userIdList = userFriends.stream().map(UserFriend::getFriendId).collect(Collectors.toList());
            return ResponseData.success(iUserService.list(Wrappers.<User>lambdaQuery().in(User::getId, userIdList)));
        }
        return ResponseData.success(new ArrayList<>());
    }

    /**
     * 添加好友
     *
     * @param fromUserId 谁添加
     * @param toUserId   好友id
     * @return
     */
    @PostMapping("/addFriend")
    public ResponseData<Boolean> addFriend(String fromUserId, String toUserId) {
        return ResponseData.success(iUserFriendService.addFriend(fromUserId, toUserId));
    }

    /**
     * 删除好友
     *
     * @param fromUserId
     * @param toUserId
     * @return
     */
    @PostMapping("/deleteFriend")
    public ResponseData<Boolean> deleteFriend(String fromUserId, String toUserId) {
        return ResponseData.success(iUserFriendService.deleteFriend(fromUserId, toUserId));
    }

    /**
     * 添加个人名片信息
     *
     * @param user
     * @return
     */
    @PostMapping("insertUser")
    public ResponseData<Boolean> insertPersonal(@RequestBody User user){
        return ResponseData.success(iUserService.save(user));
    }

    /**
     * 判断是否存在好友关系
     *
     * @param fromUserId
     * @param toUserId
     * @return  true 存在朋友关系，  false 不存在朋友关系
     */
    @GetMapping("/isFriend")
    public ResponseData<Boolean> isFriend(String fromUserId, String toUserId) {
        Boolean ret = false;
        List<UserFriend> userFriends = iUserFriendService.list(Wrappers.<UserFriend>lambdaQuery().eq(UserFriend::getUserId, fromUserId)
                .eq(UserFriend::getFriendId,toUserId)
        );
        if (CollUtil.isNotEmpty(userFriends)) {
            ret = true;
        }
        return ResponseData.success(ret);
    }

    /**
     * 修改个人名片信息
     *
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public ResponseData<Boolean> updateByIdPersonalInformation(@RequestBody User user){
        return ResponseData.success(iUserService.updateById(user));
    }

    /**
     * 查询个人名片信息
     *
     * @param id
     * @return
     */
    @GetMapping("selectUser")
    public ResponseData<User> selectPersonalInformation(String id){
        return ResponseData.success(iUserService.getById(id));
    }
}
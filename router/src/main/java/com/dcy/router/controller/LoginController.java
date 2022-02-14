package com.dcy.router.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import com.dcy.common.utils.BPwdEncoderUtil;
import com.dcy.common.utils.JwtUtil;
import com.dcy.router.model.GroupUser;
import com.dcy.router.model.User;
import com.dcy.router.model.UserEquipment;
import com.dcy.router.service.IGroupUserService;
import com.dcy.router.service.IUserService;
import com.dcy.router.service.UserEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/22 15:53
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IGroupUserService iGroupUserService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserEquipmentService userEquipmentService;
    @NacosInjected
    private NamingService namingService;


    /**
     * 登录
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseData<String> login(@RequestBody User user) throws Exception {
        // ----------------可以不调用------------------------
        //多端登录方法
        /*User user1 = iUserService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (user1 == null) {
            return ResponseData.error("用户名和密码错误，请重新输入");
        }
        if (!BPwdEncoderUtil.matches(user.getPassword(), user1.getPassword().replace("{bcrypt}", ""))) {
            return ResponseData.error("用户名和密码错误，请重新输入");
        }
        // 获取群组列表
        List<GroupUser> groupUsers = iGroupUserService.list(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserId, user1.getId()));
        if (CollUtil.isNotEmpty(groupUsers)) {
            List<String> groupIds = groupUsers.stream().map(GroupUser::getGroupId).collect(Collectors.toList());
            user1.setGroupIds(groupIds);
        }
        return ResponseData.success("请求成功", JwtUtil.generateToken(JSON.toJSONString(user1)));*/
        boolean logStatus;
        User user1 = iUserService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (user1 != null) {
            String userId = user1.getId();
            String equipmentId = user.getEquipmentId();
            UserEquipment userEquipment = new UserEquipment();
            userEquipment.setEquipmentId(equipmentId);
            userEquipment.setUserId(userId);
//            logStatus = userEquipmentService.singleEndedLogin(userEquipment);
            logStatus = userEquipmentService.multiportLogin(userEquipment);
        }else {
            return ResponseData.error("用户名和密码错误，请重新输入");
        }
        if (!BPwdEncoderUtil.matches(user.getPassword(), user1.getPassword().replace("{bcrypt}", ""))) {
            return ResponseData.error("用户名和密码错误，请重新输入");
        }
        // 获取群组列表
        List<GroupUser> groupUsers = iGroupUserService.list(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserId, user1.getId()));
        if (CollUtil.isNotEmpty(groupUsers)) {
            List<String> groupIds = groupUsers.stream().map(GroupUser::getGroupId).collect(Collectors.toList());
            user1.setGroupIds(groupIds);
        }
        if (logStatus){
            return ResponseData.error("该账户已在其他设备登录，禁止再次登录");
        }else {
            String token = JwtUtil.generateToken(JSON.toJSONString(user1));


            return ResponseData.success("请求成功", JwtUtil.generateToken(JSON.toJSONString(user1)));
        }

    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserInfo")
    public ResponseData<User> getUserInfo(HttpServletRequest request) throws Exception {
        String authorization = JwtUtil.getAuthToken(request);
        if (StrUtil.isBlank(authorization)) {
            authorization = request.getHeader("Authorization");
        }
        String userJson = JwtUtil.validateToken(authorization);
        User sysUserInfo = JSON.parseObject(userJson, User.class);
        sysUserInfo.setPassword(null);
        return ResponseData.success(sysUserInfo);
    }

    /**
     * 获取可用的netty addr
     * 此接口需要拦截
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getNettyServerAddr")
    public ResponseData<String> getNettyServerAddr() throws Exception {
        String addr = "";
        try {
            Instance instance = namingService.selectOneHealthyInstance("netty-server");
            addr = instance.getIp() + ":" + instance.getPort();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("没有可用的netty server");
        }
        return ResponseData.success(addr);
    }

    /**
     * 退出账号 暂时弃用
     *
     * @param userEquipment
     * @return
     */
    @GetMapping("/logout")
    public ResponseData<String> logout(UserEquipment userEquipment) {
        /*List<GroupUser> groupUsers = iGroupUserService.list(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserId, userId));
        if (CollUtil.isNotEmpty(groupUsers)) {
            redisTemplate.delete(CommonConstant.USER_LOGIN_GROUP + userId);
        }*/
        userEquipmentService.logout(userEquipment);
        return ResponseData.success();
    }
}

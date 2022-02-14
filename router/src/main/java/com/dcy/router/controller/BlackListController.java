package com.dcy.router.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import com.dcy.router.model.BlackList;
import com.dcy.router.model.User;
import com.dcy.router.service.IBlackListService;
import com.dcy.router.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *   黑名单表 前端控制器
 * </p>
 *
 * @Author：ly
 * @Description:
 * @Date: 2020/8/3 0003 上午 10:24
 */
@RestController
@RequestMapping("/black-list")
public class BlackListController {

    @Autowired
    private IBlackListService iBlackListService;

    @Autowired
    private IUserService iUserService;

    /**
     * 检查用户是否在黑名单中
     *
     * @param fromUserId  用户id
     * @param toUserId  被拉黑好友id
     * @return  true 在    false 不在
     */
    @GetMapping("/isBlackList")
    public ResponseData<Boolean> isBlackList(String fromUserId, String toUserId){
        boolean ret = false;
        List<BlackList> list = iBlackListService.list(Wrappers.<BlackList>lambdaQuery()
                .or(i -> i.eq(BlackList::getUserId, fromUserId).eq(BlackList::getBlackUserId, toUserId))
        );
        if(CollUtil.isNotEmpty(list)){
            ret = true;
        }
        return ResponseData.success(ret);
    }

    /**
     * 添加黑名单
     *
     * @param fromUserId 谁添加
     * @param toUserId   好友id
     * @return
     */
    @PostMapping("/addBlackList")
    public ResponseData<Boolean> addBlackList(String fromUserId, String toUserId) {
        return ResponseData.success(iBlackListService.addBlackList(fromUserId, toUserId));
    }

    /**
     * 删除黑名单
     *
     *@param fromUserId 谁添加
     *@param toUserId   好友id
     * @return
     */
    @PostMapping("/deleteBlackList")
    public ResponseData<Boolean> deleteBlackList(String fromUserId, String toUserId) {
        return ResponseData.success(iBlackListService.deleteBlackList(fromUserId,toUserId));
    }

    /**
     * 获取黑名单列表
     *
     *@param   userId  好友id
     * @return
     */
    @GetMapping("/getBlackListByUserId")
    public ResponseData<List<User>> getBlackListByUserId(String userId) {
        List<BlackList> blackList= iBlackListService.list(Wrappers.<BlackList>lambdaQuery().eq(BlackList::getUserId, userId));
        if (CollUtil.isNotEmpty(blackList)) {
            List<String> userIdList = blackList.stream().map(BlackList::getBlackUserId).collect(Collectors.toList());
            return ResponseData.success(iUserService.list(Wrappers.<User>lambdaQuery().in(User::getId, userIdList)));
        }
        return ResponseData.success(new ArrayList<>());
    }

}

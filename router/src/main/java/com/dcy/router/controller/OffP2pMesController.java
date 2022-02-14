package com.dcy.router.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dcy.router.model.OffP2pMes;
import com.dcy.router.service.IOffP2pMesService;

import java.util.List;

/**
 * <p>
 * 离线单聊消息表 前端控制器
 * </p>
 *
 * @author dcy
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/off-p2p-mes")
public class OffP2pMesController {

    @Autowired
    private IOffP2pMesService iOffP2pMesService;

    /**
     * 缓存p2p离线消息
     *
     * @param offP2pMes
     * @return
     */
    @PostMapping("/sendP2pOffMsg")
    public ResponseData<Boolean> sendOffMsg(@RequestBody OffP2pMes offP2pMes) {
        return ResponseData.success(iOffP2pMesService.save(offP2pMes));
    }


    /**
     * 获取离线消息
     *
     * @param userId
     * @param receiveUserId
     * @return
     */
    @GetMapping("/getP2pOffMsg")
    public ResponseData<List<OffP2pMes>> getP2pOffMsg(String userId, String receiveUserId) {
        List<OffP2pMes> list = iOffP2pMesService.list(Wrappers.<OffP2pMes>lambdaQuery()
                .or(i -> i.eq(OffP2pMes::getUserId, receiveUserId).eq(OffP2pMes::getReceiveUserId, userId))
                .or(i -> i.eq(OffP2pMes::getUserId, userId).eq(OffP2pMes::getReceiveUserId, receiveUserId))
                .orderByAsc(OffP2pMes::getSendTime)
        );
        return ResponseData.success(list);
    }
}

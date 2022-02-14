package com.dcy.router.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.common.model.ResponseData;
import com.dcy.router.model.OffP2pMes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dcy.router.model.OffGroupMes;
import com.dcy.router.service.IOffGroupMesService;

import java.util.List;

/**
 * <p>
 * 离线群聊消息表 前端控制器
 * </p>
 *
 * @author dcy
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/off-group-mes")
public class OffGroupMesController {


    @Autowired
    private IOffGroupMesService iOffGroupMesService;

    /**
     * 缓存群组离线消息
     *
     * @param offGroupMes
     * @return
     */
    @PostMapping("/sendGroupOffMsg")
    public ResponseData<Boolean> sendGroupOffMsg(@RequestBody OffGroupMes offGroupMes) {
        return ResponseData.success(iOffGroupMesService.save(offGroupMes));
    }


    /**
     * 获取离线消息
     *
     * @param groupId
     * @return
     */
    @GetMapping("/getGroupOffMsg")
    public ResponseData<List<OffGroupMes>> getGroupOffMsg(String groupId) {
        List<OffGroupMes> list = iOffGroupMesService.list(Wrappers.<OffGroupMes>lambdaQuery()
                .eq(OffGroupMes::getGroupId, groupId)
                .orderByAsc(OffGroupMes::getSendTime)
        );
        return ResponseData.success(list);
    }
}

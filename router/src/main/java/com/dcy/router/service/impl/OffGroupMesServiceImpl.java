package com.dcy.router.service.impl;

import com.dcy.router.model.OffGroupMes;
import com.dcy.router.mapper.OffGroupMesMapper;
import com.dcy.router.service.IOffGroupMesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 离线群聊消息表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-07-30
 */
@Service
@Transactional
public class OffGroupMesServiceImpl extends ServiceImpl<OffGroupMesMapper, OffGroupMes> implements IOffGroupMesService {

}

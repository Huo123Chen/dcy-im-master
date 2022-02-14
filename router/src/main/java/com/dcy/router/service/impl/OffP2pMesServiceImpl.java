package com.dcy.router.service.impl;

import com.dcy.router.model.OffP2pMes;
import com.dcy.router.mapper.OffP2pMesMapper;
import com.dcy.router.service.IOffP2pMesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 离线单聊消息表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-07-30
 */
@Service
@Transactional
public class OffP2pMesServiceImpl extends ServiceImpl<OffP2pMesMapper, OffP2pMes> implements IOffP2pMesService {

}

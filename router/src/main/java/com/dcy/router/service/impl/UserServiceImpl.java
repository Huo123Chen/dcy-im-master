package com.dcy.router.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dcy.router.mapper.UserMapper;
import com.dcy.router.model.User;
import com.dcy.router.model.UserEquipment;
import com.dcy.router.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

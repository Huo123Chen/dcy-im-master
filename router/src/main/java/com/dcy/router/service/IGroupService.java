package com.dcy.router.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dcy.router.model.Group;

/**
 * <p>
 * 群组表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
public interface IGroupService extends IService<Group> {


    Boolean createGroup(Group group);


    Boolean deleteGroup(String groupId);
}

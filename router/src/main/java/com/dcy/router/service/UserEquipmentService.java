package com.dcy.router.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dcy.router.model.UserEquipment;

/**
 * <p>
 *     用户登录 服务类
 * </p>
 * @Author：xhy
 * @Description:
 * @Date: 2020/8/6 9:20
 */
public interface UserEquipmentService extends IService<UserEquipment> {

    /**
     * <p>
     *     单端登录
     * </p>
     * @param userEquipment
     * @return
     */
    boolean singleEndedLogin(UserEquipment userEquipment);

    /**
     * <p>
     *     双端登录
     * </p>
     * @param userEquipment
     * @return
     */
    boolean doubleLogin(UserEquipment userEquipment);

    /**
     * <p>
     *     三端登录
     * </p>
     * @param userEquipment
     * @return
     */
    boolean threeLogin(UserEquipment userEquipment);

    /**
     * <p>
     *     多端登录
     * </p>
     * @param userEquipment
     * @return
     */
    boolean multiportLogin(UserEquipment userEquipment);

    /**
     * <p>
     *     退出登录
     * </p>
     * @param userEquipment
     * @return
     */
    boolean logout(UserEquipment userEquipment);
}

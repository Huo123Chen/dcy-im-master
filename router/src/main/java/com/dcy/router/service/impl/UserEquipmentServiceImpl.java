package com.dcy.router.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcy.router.config.Constant;
import com.dcy.router.mapper.UserEquipmentMapper;
import com.dcy.router.model.UserEquipment;
import com.dcy.router.service.UserEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author：xhy
 * @Description:
 * @Date: 2020/8/6 9:22
 */
@Service
@Transactional
public class UserEquipmentServiceImpl extends ServiceImpl<UserEquipmentMapper, UserEquipment> implements UserEquipmentService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 单端登录方法
     * @param userEquipment
     * @return boolean
     */
    public boolean singleEndedLogin(UserEquipment userEquipment){
        boolean loggingStatus;
        String key = userEquipment.getUserId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Map map1 = (Map) valueOperations.get(key);
        String equipmentId = null;
        if (null != map1){
            equipmentId = (String) map1.get("equipmentId");
        }
        if (null == valueOperations.get(key)){
            insertRedis(key,equipmentId);
            loggingStatus = false;
        }else if (equipmentId.equals(userEquipment.getEquipmentId())){
            loggingStatus = false;
        }else {
            loggingStatus = true;
        }
        return loggingStatus;
    }

    /**
     * <p>
     *     双端登录 服务类
     * </p>
     * @param userEquipment
     * @return
     */
    public boolean doubleLogin(UserEquipment userEquipment){
        boolean loggingStatus;
        String key = userEquipment.getUserId();
        String equipmentId = userEquipment.getEquipmentId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (Constant.WEB_DEVICE_IDENTIFICATION.equals(equipmentId)){
            key = "web" + key;
            insertRedis(key,equipmentId);
            loggingStatus = false;
        }else {
            key = "rests" + key;
            Map map1 = (Map) valueOperations.get(key);
            if (null != map1){
                equipmentId = (String) map1.get("equipmentId");
            }
            if (null == valueOperations.get(key)){
                insertRedis(key,equipmentId);
                loggingStatus = false;
            }else if(equipmentId.equals(userEquipment.getEquipmentId())){
                loggingStatus = false;
            }else {
                loggingStatus =true;
            }
        }
        return loggingStatus;
    }

    /**
     * <p>
     *     三端登录 服务类
     * </p>
     * @param userEquipment
     * @return
     */
    public boolean threeLogin(UserEquipment userEquipment){
        boolean loggingStatus;
        String key = userEquipment.getUserId();
        String equipmentId = userEquipment.getEquipmentId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (Constant.WEB_DEVICE_IDENTIFICATION.equals(equipmentId)){
            key = "web" + key;
            insertRedis(key,equipmentId);
            loggingStatus = false;
        }else if (Constant.WINDOWS_DEVICE_IDENTIFICATION.equals(equipmentId)){
            key = "windows" + key;
            insertRedis(key,equipmentId);
            loggingStatus = false;
        }else {
            key = "rests" + key;
            Map map1 = (Map) valueOperations.get(key);
            if (null != map1){
                equipmentId = (String) map1.get("equipmentId");
            }
            if (null == valueOperations.get(key)) {
                insertRedis(key,equipmentId);
                loggingStatus = false;
            }else if(equipmentId.equals(userEquipment.getEquipmentId())){
                loggingStatus = false;
            }else {
                loggingStatus =true;
            }
        }
        return loggingStatus;
    }

    /**
     * 多端登录 服务类
     * @param userEquipment
     * @return
     */
    public boolean multiportLogin(UserEquipment userEquipment){
        boolean loggingStatus;
        String key = "multiport" + userEquipment.getUserId();
        String equipmentId = userEquipment.getEquipmentId();
        insertRedis(key,equipmentId);
        loggingStatus = false;
        return loggingStatus;
    }

    /**
     * 退出登录 服务类
     * @param userEquipment
     * @return
     */
    @Override
    public boolean logout(UserEquipment userEquipment) {
        String key = "multiport" + userEquipment.getUserId();
        try {
            redisTemplate.delete(key);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return true;
    }

    /**
     * redis 插入
     * @param key
     * @param equipmentId
     */
    public void insertRedis(String key, String equipmentId){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Map<String, Object> map = new HashMap<>(16);
        map.put("equipmentId", equipmentId);
        map.put("logStatus", Constant.LOG_STATUS);
        try {
            valueOperations.set(key,map);
            redisTemplate.expire(key, Constant.HOURS, TimeUnit.HOURS);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
}

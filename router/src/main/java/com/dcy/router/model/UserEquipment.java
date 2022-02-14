package com.dcy.router.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *     用户登录设备表
 * </p>
 * @Author：xhy
 * @Description:
 * @Date: 2020/8/6 9:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_user_equipment")
@ApiModel(value = "UserEquipment", description = "登录设备表")
public class UserEquipment implements Serializable {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("设备id")
    private String equipmentId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("登录状态")
    private String loggingStatus;
}

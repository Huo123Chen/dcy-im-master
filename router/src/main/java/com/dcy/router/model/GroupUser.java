package com.dcy.router.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 组用户表
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_group_user")
@ApiModel(value="GroupUser对象", description="组用户表")
public class GroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "群组id")
    private String groupId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "群组角色 0:群主 1:群组成员")
    private Integer userRole;

    @ApiModelProperty(value = "是否禁言 0:禁言 1:正常")
    private Integer isTalk;

    @ApiModelProperty(value = "用户名")
    private String username;

    public static final String GROUP_ID = "group_id";

    public static final String USER_ID = "user_id";

}

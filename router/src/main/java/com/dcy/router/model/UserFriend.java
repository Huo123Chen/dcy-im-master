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
 * 用户好友表
 * </p>
 *
 * @author dcy
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_user_friend")
@ApiModel(value="UserFriend对象", description="用户好友表")
public class UserFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "好友id")
    private String friendId;


    public static final String USER_ID = "user_id";

    public static final String FRIEND_ID = "friend_id";

}

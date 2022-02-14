package com.dcy.router.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author：ly
 * @Description:
 * @Date: 2020/8/3 0003 上午 10:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_black_list")
@ApiModel(value="BlackList对象", description="用户黑名单表")
public class BlackList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "好友id")
    private String blackUserId;

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String BLACK_USER_ID = "black_user_Id";
}

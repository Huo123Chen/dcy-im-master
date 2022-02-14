package com.dcy.router.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 离线群聊消息表
 * </p>
 *
 * @author dcy
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_off_group_mes")
@ApiModel(value="OffGroupMes对象", description="离线群聊消息表")
public class OffGroupMes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "群id")
    private String groupId;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String GROUP_ID = "group_id";

    public static final String MESSAGE = "message";

    public static final String SEND_TIME = "send_time";

}

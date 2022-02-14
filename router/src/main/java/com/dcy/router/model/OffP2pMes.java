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
 * 离线单聊消息表
 * </p>
 *
 * @author dcy
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_off_p2p_mes")
@ApiModel(value="OffP2pMes对象", description="离线单聊消息表")
public class OffP2pMes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "接收人id")
    private String receiveUserId;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String RECEIVE_USER_ID = "receive_user_id";

    public static final String MESSAGE = "message";

    public static final String SEND_TIME = "send_time";

}

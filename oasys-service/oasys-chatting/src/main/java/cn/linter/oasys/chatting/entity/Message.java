package cn.linter.oasys.chatting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息实体类
 *
 * @author wangxiaoyang
 * @date 2020/11/17
 */
@ApiModel("文件")
@Data
@ToString
@EqualsAndHashCode
public class Message implements Serializable {

    private static final long serialVersionUID = -4484722387608220685L;
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String profilePicture;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}

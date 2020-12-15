package cn.linter.oasys.information.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@ApiModel("公告")
@Data
@ToString
@EqualsAndHashCode
public class Announcement implements Serializable {

    private static final long serialVersionUID = 673395149825805162L;
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
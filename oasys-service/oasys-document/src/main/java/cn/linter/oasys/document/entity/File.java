package cn.linter.oasys.document.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@ApiModel("文件")
@Data
@ToString
@EqualsAndHashCode
public class File implements Serializable {

    private static final long serialVersionUID = 223089641003572200L;
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String name;
    /**
     * 路径
     */
    @ApiModelProperty("路径")
    private String path;
    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;
    /**
     * 大小
     */
    @ApiModelProperty("大小")
    private String size;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * 父级ID
     */
    @ApiModelProperty("父级ID")
    private Long parentId;
    /**
     * 是否共享
     */
    @ApiModelProperty("是否共享")
    private Boolean shared;
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
    /**
     * content-type
     */
    @ApiModelProperty("content-type")
    private String contentType;

    public boolean isFolder() {
        return "文件夹".equals(this.type);
    }

}
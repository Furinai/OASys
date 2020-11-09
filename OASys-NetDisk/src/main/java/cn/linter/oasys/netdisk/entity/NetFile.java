package cn.linter.oasys.netdisk.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/09
 */
@ApiModel("文件")
public class NetFile implements Serializable {

    private static final long serialVersionUID = 559084664701137480L;
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
    private Integer userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * 父级ID
     */
    @ApiModelProperty("父级ID")
    private Integer parentId;
    /**
     * 是否公共
     */
    @ApiModelProperty("是否公共")
    private Boolean isPublic;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}
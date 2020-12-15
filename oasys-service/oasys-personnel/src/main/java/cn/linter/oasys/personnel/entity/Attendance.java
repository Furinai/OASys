package cn.linter.oasys.personnel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 考勤实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@ApiModel("考勤")
@Data
@ToString
@EqualsAndHashCode
public class Attendance implements Serializable {

    private static final long serialVersionUID = 341396327714176235L;
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;
    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private LocalDate clockDate;
    /**
     * 签到时间
     */
    @ApiModelProperty("签到时间")
    private Object clockInTime;
    /**
     * 签退时间
     */
    @ApiModelProperty("签退时间")
    private Object clockOutTime;
    /**
     * 打卡描述
     */
    @ApiModelProperty("打卡描述")
    private String clockDescription;

}
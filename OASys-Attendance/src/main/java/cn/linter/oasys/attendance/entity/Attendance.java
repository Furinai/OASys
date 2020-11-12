package cn.linter.oasys.attendance.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 考勤实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@ApiModel("考勤")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getClockDate() {
        return clockDate;
    }

    public void setClockDate(LocalDate clockDate) {
        this.clockDate = clockDate;
    }

    public Object getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Object clockInTime) {
        this.clockInTime = clockInTime;
    }

    public Object getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(Object clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getClockDescription() {
        return clockDescription;
    }

    public void setClockDescription(String clockDescription) {
        this.clockDescription = clockDescription;
    }

}
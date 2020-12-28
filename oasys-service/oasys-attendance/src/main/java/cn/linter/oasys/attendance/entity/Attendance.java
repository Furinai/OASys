package cn.linter.oasys.attendance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 考勤实体类
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@Data
@ToString
@EqualsAndHashCode
public class Attendance implements Serializable {

    private static final long serialVersionUID = 627660542130177717L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 打卡日期
     */
    private LocalDate clockDate;
    /**
     * 签到时间
     */
    private LocalTime clockInTime;
    /**
     * 签退时间
     */
    private LocalTime clockOutTime;
    /**
     * 迟到分钟数
     */
    private Short comeLateMinutes;
    /**
     * 早退分钟数
     */
    private Short leaveEarlyMinutes;

}
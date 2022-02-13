package cn.linter.oasys.attendance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
public class Attendance {

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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
    private Integer comeLateMinutes;
    /**
     * 早退分钟数
     */
    private Integer leaveEarlyMinutes;

}
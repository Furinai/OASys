package cn.linter.oasys.attendance.service;

import cn.linter.oasys.attendance.entity.Attendance;

import java.util.List;

/**
 * 考勤服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
public interface AttendanceService {

    /**
     * 通过用户ID和日期查询所有考勤
     *
     * @param userId 用户ID
     * @param year   年
     * @param month  月
     * @param day    日
     * @return 考勤列表
     */
    List<Attendance> listByUserIdAndClockDate(Long userId, Integer year, Integer month, Integer day);

    /**
     * 新增考勤
     *
     * @param userId 用户ID
     * @return 考勤实例
     */
    Attendance create(Long userId);

    /**
     * 更新考勤
     *
     * @param id 考勤ID
     * @return 考勤实例
     */
    Attendance update(Long id);

}
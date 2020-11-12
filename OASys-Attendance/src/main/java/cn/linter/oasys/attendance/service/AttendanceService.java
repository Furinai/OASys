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
     * 通过配置名查询配置值
     *
     * @param name 配置名
     * @return 配置值
     */
    String querySettingByName(String name);

    /**
     * 通过用户ID查询单个考勤
     *
     * @param userId 用户ID
     * @return 单个考勤
     */
    Attendance queryByUserId(Long userId);

    /**
     * 通过用户ID分页查询所有考勤
     *
     * @param userId 用户ID
     * @param year   年
     * @param month  月
     * @return 考勤列表
     */
    List<Attendance> listByUserId(Long userId, int year, int month);

    /**
     * 新增考勤
     *
     * @param attendance 考勤实例
     * @return 考勤实例
     */
    Attendance create(Attendance attendance);

    /**
     * 更新考勤
     *
     * @param attendance 考勤实例
     * @return 考勤实例
     */
    Attendance update(Attendance attendance);

}
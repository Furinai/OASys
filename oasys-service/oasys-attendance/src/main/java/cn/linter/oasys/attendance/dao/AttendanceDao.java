package cn.linter.oasys.attendance.dao;

import cn.linter.oasys.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@Mapper
public interface AttendanceDao {

    /**
     * 通过配置名查询配置值
     *
     * @param name 配置名
     * @return 配置值
     */
    String selectSettingValueByName(String name);

    /**
     * 通过ID查询单个考勤记录
     *
     * @param id 考勤记录ID
     * @return 单个考勤记录
     */
    Attendance selectById(Long id);

    /**
     * 通过用户ID和日期查询所有考勤记录
     *
     * @param userId 用户ID
     * @param year   年
     * @param month  月
     * @param day    日
     * @return 考勤记录列表
     */
    List<Attendance> listByUserIdAndClockDate(@Param("userId") Long userId, @Param("year") Integer year,
                                              @Param("month") Integer month, @Param("day") Integer day);

    /**
     * 新增考勤记录
     *
     * @param attendance 考勤实例
     * @return 影响行数
     */
    int insert(Attendance attendance);

    /**
     * 通过ID更新考勤记录
     *
     * @param attendance 考勤实例
     * @return 影响行数
     */
    int updateById(Attendance attendance);

}
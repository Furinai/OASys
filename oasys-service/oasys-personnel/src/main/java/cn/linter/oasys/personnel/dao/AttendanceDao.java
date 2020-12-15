package cn.linter.oasys.personnel.dao;

import cn.linter.oasys.personnel.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@Mapper
public interface AttendanceDao {

    /**
     * 通过配置名查询配置值
     *
     * @param name 配置名
     * @return 配置值
     */
    String selectSettingByName(String name);

    /**
     * 通过用户ID查询单个考勤
     *
     * @param userId 用户ID
     * @return 单个考勤
     */
    Attendance selectByUserId(Long userId);

    /**
     * 通过用户ID查询所有考勤
     *
     * @param userId 用户ID
     * @param year   年
     * @param month  月
     * @return 考勤列表
     */
    List<Attendance> listByUserId(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    /**
     * 新增考勤
     *
     * @param attendance 考勤实例
     * @return 影响行数
     */
    int insert(Attendance attendance);

    /**
     * 更新考勤
     *
     * @param attendance 考勤实例
     * @return 影响行数
     */
    int update(Attendance attendance);

}
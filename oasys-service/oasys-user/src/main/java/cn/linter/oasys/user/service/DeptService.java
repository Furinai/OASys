package cn.linter.oasys.user.service;

import cn.linter.oasys.user.entity.Dept;
import com.github.pagehelper.PageInfo;

/**
 * 部门服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/15
 */
public interface DeptService {

    /**
     * 通过ID查询单个部门
     *
     * @param id 部门ID
     * @return 单个部门
     */
    Dept query(Integer id);

    /**
     * 分页查询所有部门
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 部门列表
     */
    PageInfo<Dept> list(int pageNumber, int pageSize);

    /**
     * 新增部门
     *
     * @param dept 部门实例
     * @return 部门实例
     */
    Dept create(Dept dept);

    /**
     * 更新部门
     *
     * @param dept 部门实例
     * @return 部门实例
     */
    Dept update(Dept dept);

    /**
     * 删除部门
     *
     * @param id 部门ID
     * @return 是否成功
     */
    boolean delete(Integer id);

}
package cn.linter.oasys.oauth.dao;

import cn.linter.oasys.oauth.entity.Principal;
import org.apache.ibatis.annotations.Mapper;

/**
 * Principal数据库访问层
 *
 * @author wangxiaoyang
 * @date 2020/11/04
 */
@Mapper
public interface PrincipalDao {

    /**
     * 通过用户名查询Principal
     *
     * @param username 用户名
     * @return 单个Principal
     */
    Principal getPrincipalByUsername(String username);

}

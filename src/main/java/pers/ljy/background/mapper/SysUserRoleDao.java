package pers.ljy.background.mapper;


import java.util.List;

import pers.ljy.background.model.SysUserRoleEntity;

/***
 * 文件名称: SysUserRoleDao.java
 * 文件描述: 用户角色dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface SysUserRoleDao {
    /**
     * 根据主键删除数据库的记录
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     * @param record
     */
    int insert(SysUserRoleEntity record);

    /**
     * 根据主键获取一条数据库记录
     * @param id
     */
    SysUserRoleEntity selectByPrimaryKey(Integer id);

    /**
     * 获取全部数据库记录
     */
    List<SysUserRoleEntity> selectAll();

    /**
     * 根据主键来更新数据库记录
     * @param record
     */
    int updateByPrimaryKey(SysUserRoleEntity record);
}
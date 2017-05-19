package pers.ljy.background.mapper;




import org.apache.ibatis.annotations.Mapper;

import pers.ljy.background.model.SysLogsEntity;
import pers.ljy.background.share.dao.BaseDao;

/***
 * 文件名称: SysLogsDao.java
 * 文件描述: 日志纪录dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Mapper
public interface SysLogsDao extends BaseDao<SysLogsEntity, Integer>{
    
}
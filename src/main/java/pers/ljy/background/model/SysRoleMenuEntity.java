package pers.ljy.background.model;


import java.io.Serializable;
import java.util.Date;

/***
 * 角色菜单资源
 * @author ljy
 *
 */
public class SysRoleMenuEntity implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 创建人
     */
    private Date createDate;

    /**
     * 创建时间
     */
    private Integer createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_role_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id_
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 角色ID
     * @return role_id_
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 菜单ID
     * @return menu_id_
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 菜单ID
     * @param menuId
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * 创建人
     * @return create_date_
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建人
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建时间
     * @return create_user_id_
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建时间
     * @param createUserId
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
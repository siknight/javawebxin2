package com.lisi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色的数据模型
 */
@Entity
@Table(name="roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
    @Column(name="role_name")
    private String roleName;
    @Column(name="role_memo")
    private String roleMemo;
    /**
     * 多对多
     */
    @ManyToMany
    @JoinTable(
            //1.表名字
            name = "user_role",
            joinColumns = {
                //2中间表user_role_rel字段关联sys_role表的主键字段role_id
                @JoinColumn(name="role_id",referencedColumnName="role_id")},
                //3中间表user_role_rel的字段关联sys_user表的主键user_id
            inverseJoinColumns={
                    @JoinColumn(name="user_id",referencedColumnName="user_id")
    })

    private Set<Users> users = new HashSet<Users>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}

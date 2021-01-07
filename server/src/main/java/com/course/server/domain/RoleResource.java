package com.course.server.domain;

public class RoleResource {
    private Integer id;

    private String roleId;

    private String resourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoleResource{");
        sb.append("id=").append(id);
        sb.append(", roleId='").append(roleId).append('\'');
        sb.append(", resourceId='").append(resourceId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
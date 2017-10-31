package com.ecom.entity.example;

import java.io.Serializable;

public class RoleWithBLOBs extends Role implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_tac
     *
     * @mbggenerated
     */
    private String roleTac;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_node
     *
     * @mbggenerated
     */
    private String roleNode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_cell
     *
     * @mbggenerated
     */
    private String roleCell;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table role
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_tac
     *
     * @return the value of role.role_tac
     *
     * @mbggenerated
     */
    public String getRoleTac() {
        return roleTac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_tac
     *
     * @param roleTac the value for role.role_tac
     *
     * @mbggenerated
     */
    public void setRoleTac(String roleTac) {
        this.roleTac = roleTac == null ? null : roleTac.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_node
     *
     * @return the value of role.role_node
     *
     * @mbggenerated
     */
    public String getRoleNode() {
        return roleNode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_node
     *
     * @param roleNode the value for role.role_node
     *
     * @mbggenerated
     */
    public void setRoleNode(String roleNode) {
        this.roleNode = roleNode == null ? null : roleNode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_cell
     *
     * @return the value of role.role_cell
     *
     * @mbggenerated
     */
    public String getRoleCell() {
        return roleCell;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_cell
     *
     * @param roleCell the value for role.role_cell
     *
     * @mbggenerated
     */
    public void setRoleCell(String roleCell) {
        this.roleCell = roleCell == null ? null : roleCell.trim();
    }
}
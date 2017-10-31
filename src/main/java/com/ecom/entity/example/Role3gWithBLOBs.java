package com.ecom.entity.example;

import java.io.Serializable;

public class Role3gWithBLOBs extends Role3g implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_3g.role_rnc
     *
     * @mbggenerated
     */
    private String roleRnc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_3g.role_node
     *
     * @mbggenerated
     */
    private String roleNode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_3g.role_cell
     *
     * @mbggenerated
     */
    private String roleCell;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_3g.role_rnc
     *
     * @return the value of role_3g.role_rnc
     *
     * @mbggenerated
     */
    public String getRoleRnc() {
        return roleRnc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_3g.role_rnc
     *
     * @param roleRnc the value for role_3g.role_rnc
     *
     * @mbggenerated
     */
    public void setRoleRnc(String roleRnc) {
        this.roleRnc = roleRnc == null ? null : roleRnc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_3g.role_node
     *
     * @return the value of role_3g.role_node
     *
     * @mbggenerated
     */
    public String getRoleNode() {
        return roleNode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_3g.role_node
     *
     * @param roleNode the value for role_3g.role_node
     *
     * @mbggenerated
     */
    public void setRoleNode(String roleNode) {
        this.roleNode = roleNode == null ? null : roleNode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_3g.role_cell
     *
     * @return the value of role_3g.role_cell
     *
     * @mbggenerated
     */
    public String getRoleCell() {
        return roleCell;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_3g.role_cell
     *
     * @param roleCell the value for role_3g.role_cell
     *
     * @mbggenerated
     */
    public void setRoleCell(String roleCell) {
        this.roleCell = roleCell == null ? null : roleCell.trim();
    }
}
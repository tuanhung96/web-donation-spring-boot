package com.example.asm1.entity;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="role_name")
    private String roleName;

    public Role() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", roleName=" + roleName + "]";
    }

}


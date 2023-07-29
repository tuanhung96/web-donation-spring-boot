package com.example.asm1.entity;

import jakarta.persistence.*;

@Entity
@Table(name="donation")
public class Donation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="organization_name")
    private String organizationName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="money")
    private Integer money;

    @Column(name="status")
    private Integer status;

    @Column(name="description")
    private String description;

    public Donation() {
        // TODO Auto-generated constructor stub
    }

    public Donation(String code, String name, String startDate, String endDate, String organizationName,
                    String phoneNumber, Integer money, Integer status, String description) {
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizationName = organizationName;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.status = status;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Donation [id=" + id + ", code=" + code + ", name=" + name + ", startDate=" + startDate + ", endDate="
                + endDate + ", organizationName=" + organizationName + ", phoneNumber=" + phoneNumber + ", money="
                + money + ", status=" + status + ", description=" + description + "]";
    }


}

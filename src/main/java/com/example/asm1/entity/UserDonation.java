package com.example.asm1.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user_donation")
public class UserDonation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="money")
    private Integer money;

    @Column(name="date")
    private String date;

    @Column(name="message")
    private String message;

    @Column(name="status")
    private Integer status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="donation_id")
    private Donation donation;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public UserDonation() {
        // TODO Auto-generated constructor stub
    }

    public UserDonation(String name, Integer money, String date, String message, Integer status, Donation donation, User user) {
        this.name = name;
        this.money = money;
        this.date = date;
        this.message = message;
        this.status = status;
        this.donation = donation;
        this.user = user;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDonation [id=" + id + ", name=" + name + ", money=" + money + ", date=" + date + ", message="
                + message + ", status=" + status + ", donation=" + donation + ", user=" + user + "]";
    }

}

